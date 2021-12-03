package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    TextView tvLoginHere;
    EditText suName, suUsername, suPassword, suPhone;
    Button btnSu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        tvLoginHere = (TextView) findViewById(R.id.loginHere);
        suName = (EditText) findViewById(R.id.suName);
        suUsername = (EditText) findViewById(R.id.suUsername);
        suPhone = (EditText) findViewById(R.id.suPhone);
        suPassword = (EditText) findViewById(R.id.suPassword);

        tvLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void setSignUp(View v){
        String name = suName.getText().toString();
        String userName = suUsername.getText().toString();
        String pNum = suPhone.getText().toString();
        String password = suPassword.getText().toString();

        if(name.equals("") || userName.equals("") || pNum.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(), "Please Enter The Required Information", Toast.LENGTH_SHORT).show();
        } else if(pNum == null || pNum.length() < 10){
            Toast.makeText(getApplicationContext(), "Phone Number Must Be Ten Digits", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            //Uri webpage = Uri.parse("https://www.android.com");
            //Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
            finish();
            Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
        }
    }
}















