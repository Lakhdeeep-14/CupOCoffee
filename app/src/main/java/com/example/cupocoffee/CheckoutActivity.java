package com.example.cupocoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {
    float backPage;
    TextView before_text;

    float tvq;
    float tsp;
    float Total_AfterTax;
    TextView tvq_tax;
    TextView tsp_tax;
    TextView after_tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        before_text = (TextView)findViewById(R.id.textView9);

        backPage = getIntent().getExtras().getFloat("value");
        before_text.setText(Float.toString(backPage));


        tvq_tax = (TextView)findViewById(R.id.textView12);
        tsp_tax = (TextView)findViewById(R.id.textView13);
        after_tax = (TextView)findViewById(R.id.textView14);

        tvq = (float)(backPage*.10);

        tvq_tax.setText(Float.toString(tvq));

        tsp = (float)(0.05*backPage);
        tsp_tax.setText(Float.toString(tsp));

        Total_AfterTax = tvq+tsp+backPage;
        after_tax.setText(Float.toString(Total_AfterTax));
    }
}
