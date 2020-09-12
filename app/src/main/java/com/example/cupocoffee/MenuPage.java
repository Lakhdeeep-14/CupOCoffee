package com.example.cupocoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MenuPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    int hotCount;
    int chaiCount;
    int icedCount;

    Button inc_hot;
    Button inc_chai;
    Button inc_iced;

    Button dec_hot;
    Button dec_chai;
    Button dec_iced;

    TextView Qua_hot;
    TextView Qua_chai;
    TextView Qua_iced;

    float hot_price;
    float chai_price;
    float iced_price;
    float totalMoney;

    Button Checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

navigationView.setNavigationItemSelectedListener(this);

navigationView.setCheckedItem(R.id.home);

        inc_hot = (Button) findViewById(R.id.increaseQuantity_hot);
        dec_hot = (Button)findViewById(R.id.decreaseQuantity_hot);

        Qua_hot = (TextView)findViewById(R.id.Quantity_hot);

        inc_chai = (Button)findViewById(R.id.increaseQuantity_chai);
        dec_chai = (Button)findViewById(R.id.decreaseQuantity_chai);

        Qua_chai = (TextView)findViewById(R.id.Quantity_chai);

        inc_iced = (Button)findViewById(R.id.increaseQuantity_iced);
        dec_iced = (Button)findViewById(R.id.decreaseQuantity_iced);

        Qua_iced = (TextView) findViewById(R.id.Quantity_iced);


        Checkout = (Button)findViewById(R.id.button_checkout);

        inc_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotCount++;
                Qua_hot.setText(Integer.toString(hotCount));
                hot_price = (float) (hotCount*7.99);
            }
        });


        dec_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hotCount>0)
                    hotCount--;
                Qua_hot.setText(Integer.toString(hotCount));
                hot_price = (float)(hot_price*7.99);
            }
        });


        inc_chai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chaiCount++;
                Qua_chai.setText(Integer.toString(chaiCount));
                chai_price = (float)(chaiCount*8.99);
            }
        });

        dec_chai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chaiCount>0)
                    chaiCount--;
                Qua_chai.setText(Integer.toString(chaiCount));
                chai_price = (float)(chaiCount*8.99);
            }
        });


        inc_iced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icedCount++;
                Qua_iced.setText(Integer.toString(icedCount));
                iced_price = (float)(icedCount*9.49);
            }
        });

        dec_iced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(icedCount>0)
                    icedCount--;
                Qua_iced.setText(Integer.toString(icedCount));
                iced_price = (float)(icedCount*9.49);
            }
        });


        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalMoney=(float)(hot_price+chai_price+iced_price);
                Intent intent = new Intent(MenuPage.this,CheckoutActivity.class);
                intent.putExtra("value",totalMoney);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()){
            case R.id.home:
                break;
            case R.id.profile:
                Intent g = new Intent(this , ProfileActivity.class);
                startActivity(g);
                break;
            case R.id.contactUs:
                Intent contact = new Intent(this , ContactUsActivity.class);
                startActivity(contact);
                break;
        }
        return true;
    }

}

