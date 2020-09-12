package com.example.cupocoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sign_in extends AppCompatActivity {
Button instruction;

private Retrofit retrofit;
private RetrofitInterface retrofitInterface;
private String BASE_URL = "http://10.0.2.2:3000";

EditText emailEdit;
EditText passwordEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        setContentView(R.layout.activity_sign_in);
        instruction = (Button)findViewById(R.id.button_login);

       emailEdit = (EditText)findViewById(R.id.signin_email);
       passwordEdit = (EditText)findViewById(R.id.signin_password);


        instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String , String >  map = new HashMap<>();

                map.put("email" ,emailEdit.getText().toString());
                map.put("password" , passwordEdit.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if(response.code()==200){
                               Intent intent = new Intent(sign_in.this , InstructionActivity.class);
                startActivity(intent);
                        }else if(response.code()==404){
                            Toast.makeText(sign_in.this , "Invalid Credentials" , Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(sign_in.this , t.getMessage() , Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }
}
