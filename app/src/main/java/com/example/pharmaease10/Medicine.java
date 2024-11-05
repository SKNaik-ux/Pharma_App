package com.example.pharmaease10;

public class Medicine {
    private String name;
    private String description;
    private String expDate;
    private String imageUrl;
    private String price;
    private String quantity;
    private String storeName;     // Store name field
    private String storeAddress;  // Store address field

    // Default constructor required for calls to DataSnapshot.getValue(Medicine.class)
    public Medicine() {}

    public Medicine(String name, String description, String expDate, String imageUrl, String price, String quantity, String storeName, String storeAddress) {
        this.name = name;
        this.description = description;
        this.expDate = expDate;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    // Getters and setters for all fields
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getExpDate() { return expDate; }
    public String getImageUrl() { return imageUrl; }
    public String getPrice() { return price; }
    public String getQuantity() { return quantity; }
    public String getStoreName() { return storeName; }
    public String getStoreAddress() { return storeAddress; }

    public void setStoreName(String storeName) { this.storeName = storeName; }
    public void setStoreAddress(String storeAddress) { this.storeAddress = storeAddress; }
}
