package com.example.emobilis.elimuassistant;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class StudentActivity extends AppCompatActivity {
    ListView list;
    ArrayList<Students> users;
    CustomAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        list=findViewById(R.id.ListWetu);
        users=new ArrayList<>();
        adapter=new CustomAdapter(this,users);
        dialog=new ProgressDialog(this);
        dialog.setTitle("loading");
        dialog.setMessage("Please wait....");
        //connect to the table to fetch data
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()){
                    Students user=snap.getValue(Students.class);
                    users.add(user);
                    Collections.reverse(users);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentActivity.this, "Database Unreachable", Toast.LENGTH_SHORT).show();
            }
        });
        list.setAdapter(adapter);
    }
}
