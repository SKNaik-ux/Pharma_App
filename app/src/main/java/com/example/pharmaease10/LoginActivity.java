package com.example.pharmaease10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {


    EditText enternumlogin;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        TextView signintext = findViewById(R.id.signintext);
        signintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });




        enternumlogin = findViewById(R.id.inputmobilelogin);
        loginbtn = findViewById(R.id.loginbutton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enternumlogin.getText().toString().trim().isEmpty()){
                    if((enternumlogin.getText().toString().trim()).length() == 10){
//                        Intent intent = new Intent(getApplicationContext(),OTPverificationActivity.class);
//                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this,"Please enter correct number",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"Please enter mobile number",Toast.LENGTH_SHORT).show();
                }
            }
        });


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Login_Activity), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });




    }
}