package com.example.cupocoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    String name ;
    String email;

    TextView emailaddress;
    TextView Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.profile);

        name=getIntent().getExtras().getString("name");
        email=getIntent().getExtras().getString("email");

        emailaddress = (TextView)findViewById(R.id.email_profile);
        Username= (TextView)findViewById(R.id.username_profile);

        emailaddress.setText(email);

        Username.setText(name);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this, MenuPage.class);
                startActivity(intent);
                break;
            case R.id.profile:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
                break;
            case R.id.contactUs:
                Intent contact = new Intent(this, ContactUsActivity.class);
                startActivity(contact);
                break;
        }
        return true;

    }
}