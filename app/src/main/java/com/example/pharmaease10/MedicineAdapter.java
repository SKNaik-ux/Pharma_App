package com.example.pharmaease10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {
    private List<Medicine> medicineList;
    private Context context;

    public MedicineAdapter(List<Medicine> medicineList, Context context) {
        this.medicineList = medicineList;
        this.context = context;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_medicine, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);

        holder.nameTextView.setText(medicine.getName());
        holder.priceTextView.setText("Price: $" + medicine.getPrice());
        holder.quantityTextView.setText("Quantity: " + medicine.getQuantity());
        holder.storeNameTextView.setText("Store: " + medicine.getStoreName());
        holder.storeAddressTextView.setText("Address: " + medicine.getStoreAddress());


    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public static class MedicineViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView, storeNameTextView, storeAddressTextView;
        ImageView imageView;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            storeNameTextView = itemView.findViewById(R.id.storeNameTextView);
            storeAddressTextView = itemView.findViewById(R.id.storeAddressTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
