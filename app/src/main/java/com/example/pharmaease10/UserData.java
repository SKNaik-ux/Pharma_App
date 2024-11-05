package com.example.pharmaease10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pharmaease10.databinding.ActivityUserDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserData extends AppCompatActivity {


    ActivityUserDataBinding binding;
    String name,address;
    String age;
    String height;
    String weight;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_data);
        binding = ActivityUserDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name=binding.custName.getText().toString();
                address=binding.custAddress.getText().toString();
                age= binding.custAge.getText().toString();
                weight= binding.custWeight.getText().toString();
                height=binding.custHeight.getText().toString();


                if (!name.isEmpty() && !age.isEmpty() && !address.isEmpty() && !weight.isEmpty() && !height.isEmpty()){

                    Users users = new Users(name,age,weight,height,address);
                    db=FirebaseDatabase.getInstance();
                    reference=db.getReference("Users");
                    reference.child(name).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            binding.custName.setText("");
                            binding.custAge.setText("");
                            binding.custHeight.setText("");
                            binding.custWeight.setText("");
                            binding.custAddress.setText("");
                            Toast.makeText(UserData.this, "User Registration Completed!", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

                Intent intent = new Intent(UserData.this,DashboardActivity.class);
                startActivity(intent);
            }
        });

    }
}