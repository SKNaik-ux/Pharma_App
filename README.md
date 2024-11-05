# Pharmass App

A mobile application designed to help users search for medications, locate nearby pharmacies, and manage preorders. With added features like OTP verification, user profiles, and a map-based store locator, the Pharmacy App is built for convenience and security.

---

## Table of Contents
- [About the Project](#about-the-project)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Firebase Database Rules](#firebase-database-rules)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## About the Project
The Pharmacy App is a platform allowing users to search for available medicines, view detailed store information, and preorder items for pickup. Enhanced with OTP verification, personal profile management, and Google Maps integration, the app offers a smooth, secure experience for pharmacy-related needs.

## Features
- **Medicine Search**: Users can search for specific medications and view details like price and availability.
- **OTP Verification**: Secure login system using Firebase Authentication for OTP-based sign-in.
- **User Profile Management**: Users can create, view, and update their profiles for a personalized experience.
- **Nearby Pharmacy Locator**: Integrated Google Maps API enables users to find nearby pharmacies.
- **Preorder Functionality**: Option to preorder medicines, allowing for quick pickups at designated stores.

## Technologies Used
- **Android Studio** with **Java** for application development
- **Firebase Realtime Database** for storage and management of medicines and preorders
- **Firebase Authentication** for OTP verification
- **Google Maps API** for locating nearby pharmacies

## Getting Started
To get a local copy up and running, follow these steps:

### Prerequisites
- [Android Studio](https://developer.android.com/studio) installed on your system
- Firebase project setup with Realtime Database and Authentication (Phone Sign-in enabled)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/PharmacyApp.git
Here's the Markdown content for each of the sections you requested:



2. Open the project in Android Studio.


3. Download the google-services.json configuration file from your Firebase project and add it to the app directory.


4. Set up the Firebase Realtime Database URL in your application code:
   ```bash
   FirebaseDatabase database = FirebaseDatabase.getInstance("https://your-database-url.firebaseio.com");
5. Configure your Maps API key in the AndroidManifest.xml under <application>
   ```bash
   <meta-data
   android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_GOOGLE_MAPS_API_KEY"/>



## Project Structure

1)MainActivity: Handles OTP-based user authentication.

2)DashboardActivity: Allows users to search for medicines and view details of each item.

3)ProfileActivity: Manages user profile information, including personal details.

4)MapsActivity: Displays nearby pharmacies on a map for easy access.

5)MedicineAdapter: Custom RecyclerView adapter to display medicine details in a list.




## Usage

1. Login with OTP: Securely log in using OTP-based authentication.


2. Search for Medicines: Use the search bar to find medicines by name.


3. View Nearby Pharmacies: Use the map to locate pharmacies near you.


4. Manage Profile: Update personal details for a customized experience.


5. Preorder Medicines: Place preorders for selected medicines for convenient pickups.



## Contributing

Contributions are welcome! Follow these steps:

1. Fork this repository.


2. Create a feature branch (git checkout -b feature/YourFeature).


3. Commit your changes (git commit -m 'Add your feature').


4. Push to your branch (git push origin feature/YourFeature).


5. Open a Pull Request.
