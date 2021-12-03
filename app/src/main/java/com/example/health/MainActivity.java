package com.example.health;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    SummaryFragment summaryFrag = new SummaryFragment();
    BrowseFragment browseFrag = new BrowseFragment();
    ImageButton imgButton;

    private TextView steps;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.fragment_login);

        bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.summary);

        //imgButton = (ImageButton) findViewById(R.id.imgButton1);

    }

    public void imageButtonOnClick(View v) {
        String id = getResources().getResourceEntryName(v.getId());
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.summary:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, summaryFrag).commit();
                return true;

            case R.id.browse:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, browseFrag).commit();
                return true;
        }

        return false;
    }

}