package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])" +
                    "(?=.*[a-z])(?=.*[A-Z])" +
                    "(?=.*[@#$%^&+=!])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,20}" +                // at least 4 characters
                    "$");

    TextView tvLoginHere;
    EditText suName, suUsername, suPassword, suConfirmPassword;
    Button btnSu;
    boolean good;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        tvLoginHere = (TextView) findViewById(R.id.loginHere);
        suName = (EditText) findViewById(R.id.suName);
        suUsername = (EditText) findViewById(R.id.suUsername);
        suPassword = (EditText) findViewById(R.id.suPassword);
        suConfirmPassword = (EditText) findViewById(R.id.suConfirmPassword);

        tvLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        suName.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                stuff(suUsername);
                /*String newS = suName.getText().toString();

                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users/" + newS);
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Exist! Do whatever.
                            /*Toast toast = Toast.makeText(getApplicationContext(), "Username taken, Try Again", Toast.LENGTH_SHORT);
                            toast.show();
                            suUsername.setError("Username Taken");
                            good = false;
                        } else {
                            suUsername.setError(null);
                            good = true;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed, how to handle?

                    }

                });*/

            }
        });

        suUsername.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newS = suUsername.getText().toString();

                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users/" + newS);
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Exist! Do whatever.
                            /*Toast toast = Toast.makeText(getApplicationContext(), "Username taken, Try Again", Toast.LENGTH_SHORT);
                            toast.show();*/
                            suUsername.setError("Username Taken");
                            good = false;
                        } else {
                            suUsername.setError(null);
                            good = true;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed, how to handle?

                    }

                });

            }
        });

        suPassword.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newS = suPassword.getText().toString();
                //Toast.makeText(getApplicationContext(), newS, Toast.LENGTH_SHORT).show();

                if(newS.isEmpty()){
                    suPassword.setError("Field can not be empty");
                } else if(!PASSWORD_PATTERN.matcher(newS).matches()){
                    suPassword.setError("Password is too weak");
                } else {
                    suPassword.setError(null);
                }

                /*isValidPassword(newS);
                Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                Log.v("Tag", Boolean.toString(isValidPassword(newS)));*/

            }
        });

        suConfirmPassword.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newS = suConfirmPassword.getText().toString();
                String regPass = suPassword.getText().toString();
                //Toast.makeText(getApplicationContext(), newS, Toast.LENGTH_SHORT).show();

                if(newS.isEmpty()){
                    suConfirmPassword.setError("Field can not be empty");
                } else if(!PASSWORD_PATTERN.matcher(newS).matches()){
                    suConfirmPassword.setError("Password is too weak");
                } else if(!newS.equals(regPass)){
                    suConfirmPassword.setError("Passwords must match");
                } else {
                    suConfirmPassword.setError(null);
                }

                /*isValidPassword(newS);
                Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                Log.v("Tag", Boolean.toString(isValidPassword(newS)));*/

            }
        });

    }

    public void setSignUp(View v){

        stuff(suUsername);

        rootNode = FirebaseDatabase.getInstance("https://health-d58d7-default-rtdb.firebaseio.com/");
        reference = rootNode.getReference("users");


        String name = suName.getText().toString();
        String userName = suUsername.getText().toString();
        String password1 = suPassword.getText().toString();
        String password2 = suConfirmPassword.getText().toString();

        if(name.equals("") || userName.equals("") || password1.equals("") || password2.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid, Try again", Toast.LENGTH_SHORT);
            toast.show();
        } else if(!password1.equals(password2)){
            Toast toast = Toast.makeText(getApplicationContext(), "Password Must Match", Toast.LENGTH_SHORT);
            toast.show();
        } else if(!good){
            Toast toast = Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            //usernameExists(name, userName, password1);
            //reference.child("users").setValue(name);
            SignUpHelperClass shc = new SignUpHelperClass(name, userName, password1);
            reference.child(userName).setValue(shc);

            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }



        /*if(name.equals("") || userName.equals("")){
            Toast.makeText(getApplicationContext(), "Please Enter The Required Information", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            //Uri webpage = Uri.parse("https://www.android.com");
            //Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
            finish();
            Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void stuff(EditText rando){

        rando.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newS = rando.getText().toString();

                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users/" + newS);
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Exist! Do whatever.
                            /*Toast toast = Toast.makeText(getApplicationContext(), "Username taken, Try Again", Toast.LENGTH_SHORT);
                            toast.show();*/
                            rando.setError("Username Taken");
                            good = false;
                        } else {
                            rando.setError(null);
                            good = true;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed, how to handle?

                    }

                });

            }
        });

    }

    /*public void usernameExists(String name, String checkName, String pass){


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("users/" + checkName);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Exist! Do whatever.
                    Toast toast = Toast.makeText(getApplicationContext(), "Username taken, Try Again", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    // Don't exist! Do something.

                    HelperClass hp = new HelperClass(checkName, pass);

                    hp.setUsername(checkName);
                    hp.setPassword(pass);

                    Toast toast = Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT);
                    toast.show();

                    reference.child(checkName).setValue(hp);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed, how to handle?

            }

        });
    }*/
}















