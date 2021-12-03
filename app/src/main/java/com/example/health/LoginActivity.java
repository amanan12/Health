package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.text.Editable;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etUsername, etPassword, etPhone;
    TextView tvSignUpHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvSignUpHere = (TextView) findViewById(R.id.signUpHere);
        //etPhone = (EditText) findViewById(R.id.etPhone);

        tvSignUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*etPassword.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newS = s.toString();
                Toast.makeText(getApplicationContext(), newS, Toast.LENGTH_SHORT).show();

                //isValidPassword(newS);

                Log.v("Tag", Boolean.toString(isValidPassword(newS)));

            }
        });*/

    }

    public void setBtnLogin(View v) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        //String phone = etPhone.getText().toString();



        /*
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //Uri webpage = Uri.parse("https://www.android.com");
        //Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
        finish();*/
    }

    public static boolean isValidPassword(String password) {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=!])"
                + "(?=\\S+$).{6,2000}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }
}