package com.example.pharmaease10;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPverificationActivity extends AppCompatActivity {


    EditText digit1, digit2, digit3, digit4, digit5, digit6;
    String getOTPbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otpverification);


        final Button otpverifybtn = findViewById(R.id.verifybtn);

        digit1 = findViewById(R.id.otpdigit1);
        digit2 = findViewById(R.id.otpdigit2);
        digit3 = findViewById(R.id.otpdigit3);
        digit4 = findViewById(R.id.otpdigit4);
        digit5 = findViewById(R.id.otpdigit5);
        digit6 = findViewById(R.id.otpdigit6);


        TextView textView = findViewById(R.id.otpverify_num);
        textView.setText(String.format("+91-%s", getIntent().getStringExtra("mobile")));

        getOTPbackend = getIntent().getStringExtra("backOTP");
        final ProgressBar loading_otpverify = findViewById(R.id.loading_otp_verify);

        otpverifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!digit1.getText().toString().trim().isEmpty() && !digit2.getText().toString().trim().isEmpty() && !digit3.getText().toString().trim().isEmpty() && !digit4.getText().toString().trim().isEmpty() && !digit5.getText().toString().trim().isEmpty() && !digit6.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(OTPverificationActivity.this,"OTP verify",Toast.LENGTH_SHORT).show();
                    String enterotp = digit1.getText().toString() +
                            digit2.getText().toString() +
                            digit3.getText().toString() +
                            digit4.getText().toString() +
                            digit5.getText().toString() +
                            digit6.getText().toString();

                    if (getOTPbackend != null) {
                        loading_otpverify.setVisibility(View.VISIBLE);
                        otpverifybtn.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getOTPbackend, enterotp
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        loading_otpverify.setVisibility(View.GONE);
                                        otpverifybtn.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), UserData.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(OTPverificationActivity.this, "Please enter the correct OTP!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(OTPverificationActivity.this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(OTPverificationActivity.this, "Please enter all numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        digitmove();


        TextView resendotplabel = findViewById(R.id.resendotp);
        resendotplabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        OTPverificationActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(OTPverificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {


                                getOTPbackend = newbackOTP;
                                Toast.makeText(OTPverificationActivity.this, "OTP resent Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.verifyotp_Activity), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    private void digitmove() {
        digit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    digit2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        digit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    digit3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        digit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    digit4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        digit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    digit5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        digit5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    digit6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}