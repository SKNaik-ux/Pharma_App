package com.example.pharmaease10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView; // Use androidx SearchView
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private MedicineAdapter medicineAdapter;
    private List<Medicine> medicineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize SearchView with the correct type
        searchView = findViewById(R.id.search_bar);

        recyclerView = findViewById(R.id.recyclerView);
        medicineList = new ArrayList<>();
        medicineAdapter = new MedicineAdapter(medicineList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(medicineAdapter);

        // Set up SearchView listener for search queries
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query.toLowerCase());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText.toLowerCase());
                return true;
            }
        });

        // Initialize the image list
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.poster1, "", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.poster2, "", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.poster3, "", ScaleTypes.CENTER_CROP));

        // Find the ImageSlider view
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
        imageSlider.startSliding(5000);

        // Profile, Maps, and Home buttons
        Button profilebtn = findViewById(R.id.btn_profile);
        Button mapsbtn = findViewById(R.id.btn_maps);
        Button homebtn = findViewById(R.id.btn_home);

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        mapsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void performSearch(String query) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("stores");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Medicine> filteredMedicineList = new ArrayList<>();
                for (DataSnapshot storeSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot medicineSnapshot : storeSnapshot.getChildren()) {
                        if (medicineSnapshot.child("medicines").exists()) {
                            Medicine medicine = medicineSnapshot.child("medicines").getValue(Medicine.class);
                            if (medicine != null) {
                                medicine.setStoreName(medicineSnapshot.child("storeName").getValue(String.class));
                                medicine.setStoreAddress(medicineSnapshot.child("storeAddress").getValue(String.class));
                                filteredMedicineList.add(medicine);
                            }
                        }
                    }
                }
                // Update the adapter with the filtered medicine list
                medicineAdapter = new MedicineAdapter(filteredMedicineList, DashboardActivity.this);
                recyclerView.setAdapter(medicineAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseError", databaseError.getMessage());
            }
        });
    }
}
