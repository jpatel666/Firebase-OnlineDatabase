package com.example.firebaseonlinedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebaseonlinedatabase.ModelClassRealtimeDatabase.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealtimeDatabaseActivity extends AppCompatActivity {


    EditText etName, etNumber;

    Button btnSave, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_database);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);


        if (getIntent().getExtras() != null) {

            String name = getIntent().getStringExtra("name");
            String number = getIntent().getStringExtra("number");

            etName.setText(name);
            etNumber.setText(number);
            btnSave.setText("Update");
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null) {

                    String editname = etName.getText().toString();
                    String editnumber = etNumber.getText().toString();

                    String userid = getIntent().getStringExtra("userid");

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("UserData").child(userid);

                    User user = new User(userid, editname, editnumber);

                    myRef.setValue(user);

                    Toast.makeText(RealtimeDatabaseActivity.this, "Update", Toast.LENGTH_SHORT).show();


                } else {
                    String name = etName.getText().toString();
                    String number = etNumber.getText().toString();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("UserData").push();

                    String userid = myRef.getKey();

                    User user = new User(userid, name, number);

                    myRef.setValue(user);

                    Toast.makeText(RealtimeDatabaseActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RealtimeDatabaseActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });

    }
}