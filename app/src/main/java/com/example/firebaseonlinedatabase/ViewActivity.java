package com.example.firebaseonlinedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.firebaseonlinedatabase.AdapterRealtimeDatabase.UserAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.recyclerView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("UserData");

        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("Error", "Error getting data", task.getException());
                }
                else {
                    Log.e("Show", String.valueOf(task.getResult().getValue()));

                    Map map = (Map) task.getResult().getValue();
                    Log.e("ShowMap",String.valueOf(map));

                    List<HashMap> list =new ArrayList<HashMap>(map.values());
                    Log.e("ShowList",String.valueOf(list));

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewActivity.this,RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    UserAdapter userAdapter = new UserAdapter(ViewActivity.this,list);
                    recyclerView.setAdapter(userAdapter);





                }
            }
        });


    }
}