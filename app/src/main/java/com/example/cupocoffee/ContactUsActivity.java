package com.example.cupocoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.client.Firebase;
import com.google.android.material.navigation.NavigationView;
public class ContactUsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    EditText namedata, emaildata, messagedata;
    Button send, details;
    Firebase firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        namedata = findViewById(R.id.namedata);
        emaildata = findViewById(R.id.emaildata);
        messagedata = findViewById(R.id.messagedata);

        send = findViewById(R.id.btn_send);
        details = findViewById(R.id.btn_details);
        Firebase.setAndroidContext(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.contactUs);


        String UniqueID =
        Settings.Secure.getString(getApplicationContext().getContentResolver(),
        Settings.Secure.ANDROID_ID);

        firebase = new Firebase("https://cupcontactus.firebaseio.com/Users" + UniqueID);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setEnabled(true);
                final String name = namedata.getText().toString();
                final String email = emaildata.getText().toString();
                final String message = messagedata.getText().toString();


                Firebase child_name = firebase.child("Name");
                child_name.setValue(name);
                if(name.isEmpty()) {
                    namedata.setError("This is a required field");
                    send.setEnabled(false);
                }
                else
                {
                    namedata.setError(null);
                    send.setEnabled(true);
                }

                Firebase child_email = firebase.child("Email");
                child_email.setValue(email);
                if(email.isEmpty())
                {
                    emaildata.setError("This is a required field!");
                    send.setEnabled(false);
                }
                else
                {
                    emaildata.setError(null);
                    send.setEnabled(true);
                }

                Firebase child_message = firebase.child("Message");
                child_message.setValue(message);
                if(message.isEmpty())
                {
                    messagedata.setError("This is a required field..");
                    send.setEnabled(false);
                }
                else
                {
                    messagedata.setError(null);
                    send.setEnabled(true);
                }

                Toast.makeText(ContactUsActivity.this, "Your data was sent to server!Thank you for your valuable feedback", Toast.LENGTH_SHORT).show();

                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(ContactUsActivity.this)
                                .setTitle("Sent details:")
                                .setMessage("Name - " + name + "\n\nEmail - " + email + "\n\nMessage - " + message)
                                .show();
                    }
                });


            }
        });

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