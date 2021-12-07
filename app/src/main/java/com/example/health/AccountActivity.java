package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class AccountActivity extends AppCompatActivity {

    Button btnLogout, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_account);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
    }

    public void setBtnLogout(View v){
        Intent intent = new Intent(AccountActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }

    public void setBtnUpdate(View v){
        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}