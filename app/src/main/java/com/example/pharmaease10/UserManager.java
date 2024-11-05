package com.example.pharmaease10;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserManager {
    private DatabaseReference databaseReference;
    private ArrayList<User> usersList = new ArrayList<>();

    public UserManager() {
        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        fetchAllUsers();
    }

    // Fetch all users from Firebase
    private void fetchAllUsers() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear(); // Clear list before adding new data
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user != null) {
                        usersList.add(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("UserManager", "Error fetching users: " + error.getMessage());
            }
        });
    }

    // Return the list of users
    public ArrayList<User> getUsersList() {
        return usersList;
    }
}
