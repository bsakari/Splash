package com.example.emobilis.elimuassistant;

//CustomAdapter

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Students> data;//modify here

    public CustomAdapter(Context mContext, ArrayList<Students> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// # of items in your arraylist
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);// get the actual item
    }
    @Override
    public long getItemId(int id) {
        return id;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout, parent,false);//modify here
            viewHolder = new ViewHolder();
            //modify this block of code
            viewHolder.tvnames = (TextView) convertView.findViewById(R.id.name1);
            viewHolder.tvmarks = (TextView) convertView.findViewById(R.id.marks1);
            viewHolder.tvreg = (TextView) convertView.findViewById(R.id.reg_no1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //MODIFY THIS BLOCK OF CODE
        final Students person = data.get(position);//modify here
        viewHolder.tvnames.setText(person.getName());//modify here
        viewHolder.tvmarks.setText(person.getMarks());//modify here
        viewHolder.tvreg.setText(person.getReg());//modify here
        viewHolder.mBtnDelete=convertView.findViewById(R.id.btnDelete);
        viewHolder.mBtnedit=convertView.findViewById(R.id.btnedit);
        viewHolder.mBtnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Edit");
                builder.setMessage("Are you sure you want to edit student details?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "Edit Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users/"+person.getKey());
                        ref.removeValue();
                        Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        viewHolder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "Deletion Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users/"+person.getKey());
                        ref.removeValue();
                        Toast.makeText(mContext, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return convertView;

    }
    static class ViewHolder {
        TextView tvnames;
        TextView tvmarks;
        TextView tvreg;//These don't have to be same names as the Id's
        Button mBtnDelete;
        Button mBtnedit;

    }

}


