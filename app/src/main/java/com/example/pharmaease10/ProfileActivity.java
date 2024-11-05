package com.example.pharmaease10;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pharmaease10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView profileName, profileAge, profileHeight, profileWeight, profileAddress;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Initialize UI elements
        profileName = findViewById(R.id.profile_name);
        profileAge = findViewById(R.id.profile_age);
        profileHeight = findViewById(R.id.profile_height);
        profileWeight = findViewById(R.id.profile_weight);
        profileAddress = findViewById(R.id.profile_contact);

        // Fetch and display user data

        loadUserData("Saish Naik");

    }

    private void loadUserData(String userName) {
        // Retrieve data for a specific user
        databaseReference.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve values from the database
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String age = dataSnapshot.child("age").getValue(String.class);
                    String height = dataSnapshot.child("height").getValue(String.class);
                    String weight = dataSnapshot.child("weight").getValue(String.class);
                    String address = dataSnapshot.child("address").getValue(String.class);

                    // Set the values to TextViews
                    profileName.setText(name != null ? name : "N/A");
                    profileAge.setText(age != null ? "Age: " + age : "Age: N/A");
                    profileHeight.setText(height != null ? "Height: " + height : "Height: N/A");
                    profileWeight.setText(weight != null ? "Weight: " + weight : "Weight: N/A");
                    profileAddress.setText(address != null ? "Address: " + address : "Address: N/A");
                } else {
                    // Handle case where the user does not exist
                    Toast.makeText(ProfileActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
                Toast.makeText(ProfileActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
