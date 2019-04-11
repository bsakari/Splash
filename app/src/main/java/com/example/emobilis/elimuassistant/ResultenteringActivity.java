package com.example.emobilis.elimuassistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultenteringActivity extends AppCompatActivity {
    EditText edtreg, edtname, edtmarks;
    Button btnadd, btnViewall, btnView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultentering);
        edtreg = findViewById(R.id.reg_no1);
        edtname = findViewById(R.id.name1);
        edtmarks = findViewById(R.id.marks1);
        btnadd = findViewById(R.id.addbtn1);
        btnViewall = findViewById(R.id.viewallbtn1);
        btnView = findViewById(R.id.viewbtn1);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Saving");
        dialog.setMessage("Please wait....");


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = System.currentTimeMillis();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("students/" + time);
                String reg = edtreg.getText().toString().trim();
                String name = edtname.getText().toString();
                String marks = edtmarks.getText().toString();


                if (name.isEmpty() || reg.isEmpty() | marks.isEmpty()) {
                    Toast.makeText(ResultenteringActivity.this, "Fill all the fields to Continue", Toast.LENGTH_SHORT).show();
                } else {
                    Students mtu = new Students(name, marks, reg, String.valueOf(time));
                    dialog.show();
                    ref.setValue(mtu).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(ResultenteringActivity.this, "Student details Saved Successfull", Toast.LENGTH_SHORT).show();
                                edtname.setText("");
                                edtmarks.setText("");
                                edtreg.setText("");
                            } else {
                                Toast.makeText(ResultenteringActivity.this, "Saving Failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                    btnViewall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent Intent = new Intent(getApplicationContext(), StudentActivity.class);
                            startActivity(Intent);
                        }
                    });

                }
            }
        });
    }
}




