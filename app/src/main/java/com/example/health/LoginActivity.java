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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class LoginActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])" +
                    "(?=.*[a-z])(?=.*[A-Z])" +
                    "(?=.*[@#$%^&+=!])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,20}" +                // at least 4 characters
                    "$");

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

        etUsername.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //SignUpHelperClass helperClass = new SignUpHelperClass();
                String newS = etUsername.getText().toString();

                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users/" + newS);
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Exist! Do whatever.
                            /*Toast toast = Toast.makeText(getApplicationContext(), "Username taken, Try Again", Toast.LENGTH_SHORT);
                            toast.show();*/
                            etUsername.setError(null);
                        } else {
                            etUsername.setError("Username not found");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed, how to handle?

                    }

                });

                /*isValidPassword(newS);
                Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                Log.v("Tag", Boolean.toString(isValidPassword(newS)));*/

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

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users/" + username);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    SignUpHelperClass helperClass = new SignUpHelperClass();
                    String dbPass = snapshot.getValue(SignUpHelperClass.class).getPass();
                    if(dbPass.equals(password)){
                        System.out.println(snapshot.getValue(SignUpHelperClass.class).getPass());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        etPassword.setError("Incorrect password");
                    }

                    /*SignUpHelperClass helperClass = new SignUpHelperClass();
                    String dbPass = helperClass.getPass();
                    if(dbPass.equals(password)){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    // Exist! Do whatever.
                            /*Toast toast = Toast.makeText(getApplicationContext(), "Username taken, Try Again", Toast.LENGTH_SHORT);
                            toast.show();*/
                    //String dbPass = rootRef.child("pass").toString();
                    /*if(password.equals(dbPass)){
                        //etUsername.setError(null);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }*/
                } else {
                    etUsername.setError("Username not found");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed, how to handle?

            }

        });

        //String phone = etPhone.getText().toString();
        /*
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //Uri webpage = Uri.parse("https://www.android.com");
        //Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
        finish();*/
    }

    /*public static boolean isValidPassword(String password) {

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
    }*/
}