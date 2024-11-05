package com.example.pharmaease10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {


    EditText enternumregister;
    Button proceedregister;
    TextView skiptodash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registeractivity);

        TextView logintext = findViewById(R.id.logintext);
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        skiptodash = findViewById(R.id.skiptodash);
        skiptodash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });


        ProgressBar progressBar = findViewById(R.id.loading_otp_register);
        enternumregister = findViewById(R.id.inputmobilesignup);
        proceedregister = findViewById(R.id.registerotp);
         proceedregister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!enternumregister.getText().toString().trim().isEmpty()){
                     if((enternumregister.getText().toString().trim()).length() == 10){


                        progressBar.setVisibility(View.VISIBLE);
                        proceedregister.setVisibility(View.INVISIBLE);

                         PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                 "+91" + enternumregister.getText().toString(),
                                 60,
                                 TimeUnit.SECONDS,
                                 RegisterActivity.this,
                                 new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                     @Override
                                     public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                         progressBar.setVisibility(View.GONE);
                                         proceedregister.setVisibility(View.VISIBLE);
                                     }

                                     @Override
                                     public void onVerificationFailed(@NonNull FirebaseException e) {
                                         progressBar.setVisibility(View.GONE);
                                         proceedregister.setVisibility(View.VISIBLE);
                                         Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                     }

                                     @Override
                                     public void onCodeSent(@NonNull String backOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                         progressBar.setVisibility(View.GONE);
                                         proceedregister.setVisibility(View.VISIBLE);
                                         Intent intent = new Intent(getApplicationContext(),OTPverificationActivity.class);
                                         intent.putExtra("mobile",enternumregister.getText().toString());
                                         intent.putExtra("backOTP",backOTP);
                                         startActivity(intent);
                                     }
                                 }
                         );




//                         Intent intent = new Intent(getApplicationContext(),OTPverificationActivity.class);
//                         intent.putExtra("mobile",enternumregister.getText().toString());
//                         startActivity(intent);
                     }else {
                         Toast.makeText(RegisterActivity.this,"Please enter correct number",Toast.LENGTH_SHORT).show();
                     }
                 }else {
                     Toast.makeText(RegisterActivity.this,"Please enter mobile number",Toast.LENGTH_SHORT).show();
                 }
             }
         });



//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Register_Activity), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}