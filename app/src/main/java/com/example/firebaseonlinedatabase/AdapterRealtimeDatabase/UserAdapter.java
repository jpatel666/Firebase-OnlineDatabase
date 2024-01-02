package com.example.firebaseonlinedatabase.AdapterRealtimeDatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseonlinedatabase.R;
import com.example.firebaseonlinedatabase.RealtimeDatabaseActivity;
import com.example.firebaseonlinedatabase.ViewActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyClass> {

    ViewActivity viewActivity;
    List<HashMap> list;

    public UserAdapter(ViewActivity viewActivity, List<HashMap> list) {
        this.viewActivity = viewActivity;
        this.list = list;
    }

    @NonNull
    @Override
    public UserAdapter.MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(viewActivity).inflate(R.layout.app_item_layout, parent, false);
        MyClass myClass = new MyClass(view);
        return myClass;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyClass holder, int position) {

        HashMap hashMap = list.get(position);

        String userid = (String) hashMap.get("userid");

        String name = (String) hashMap.get("name");
        holder.tvName.setText(name);

        String number = (String) hashMap.get("number");
        holder.tvNumber.setText(number);

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(viewActivity, RealtimeDatabaseActivity.class);

                intent.putExtra("userid", userid);
                intent.putExtra("name", name);
                intent.putExtra("number", number);
                
                viewActivity.startActivity(intent);
                viewActivity.finish();


            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder ab = new AlertDialog.Builder(viewActivity);

                ab.setTitle("Delete");
                ab.setMessage("Are You Sure Want To Delete?");

                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("UserData");

                        myRef.child(userid).removeValue();

                        list.remove(position);

                        notifyDataSetChanged();

                    }
                });

                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ab.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {

        TextView tvName, tvNumber;

        ImageView btnUpdate;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }
}
