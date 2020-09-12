package com.example.cupocoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sign_up extends AppCompatActivity {
    TextView signin;
    Button register;
    EditText email;
    EditText name;
    EditText password;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signin  = (TextView)findViewById(R.id.login);


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        register= (Button)findViewById(R.id.button_register);
        email = (EditText)findViewById(R.id.register_email);
        name = (EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.register_password);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String , String> map = new HashMap<>();

                map.put("name" , name.getText().toString());
                map.put("email" ,email.getText().toString());
                map.put("password" , password.getText().toString());

                Call<Void> call = retrofitInterface.executeSignUp(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==200){
                            Toast.makeText(sign_up.this, "Sign Up successfully", Toast.LENGTH_SHORT).show();
                        }else if(response.code()==400) {
                            Toast.makeText(sign_up.this , "Already registered" , Toast.LENGTH_SHORT).show();

                        }
                        }



                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(sign_up.this , t.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_up.this , sign_in.class);
                startActivity(intent);
            }
        });
    }
}
