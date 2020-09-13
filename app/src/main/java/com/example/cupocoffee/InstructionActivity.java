package com.example.cupocoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstructionActivity extends AppCompatActivity {

    String name;
    String email;
Button menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        menu = (Button)findViewById(R.id.button_menu);
        name = getIntent().getExtras().getString("name");
        email = getIntent().getExtras().getString("email");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstructionActivity.this , MenuPage.class);
                intent.putExtra("name" , name);
                intent.putExtra("email" , email);
                startActivity(intent);
            }
        });
    }
}
