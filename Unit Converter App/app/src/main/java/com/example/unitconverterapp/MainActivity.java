package com.example.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private Button btnConvert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Populate spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.scales, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        btnConvert = (Button) findViewById(R.id.button);

        addListenerOnButton();
    }

    public void addListenerOnButton(){
        btnConvert.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputEditText = (EditText) findViewById(R.id.inputEditText);
                // if no num toast
                if (inputEditText.getText().toString().trim().length() == 0) {
                    Context context = getApplicationContext();
                    CharSequence text = "Enter a value";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                } else {
                    double input = Double.parseDouble(inputEditText.getText().toString());

                    // spinner selections are the same
                    if (((Spinner)findViewById(R.id.spinner1)).getSelectedItem() == ((Spinner)findViewById(R.id.spinner2)).getSelectedItem()) {
                        Context context = getApplicationContext();
                        CharSequence text = "No conversion needed";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();
                    }
                    int spinner1_pos = spinner1.getSelectedItemPosition();
                    int spinner2_pos = spinner2.getSelectedItemPosition();
                    double result = 0;
                    // Fahrenheit to Celsius
                    if (spinner1_pos == 0 && spinner2_pos == 1){
                        result = (input - 32) * 5 / 9;
                    }
                    // Celsius to Fahrenheit
                    if (spinner1_pos == 1 && spinner2_pos == 0){
                        result = (input * 9 / 5) + 32;
                    }
                    // Set the result value
                    final TextView resultView = (TextView) findViewById(R.id.resultView);
                    resultView.setText(String.format("%.2f", result));
                }
                }
        }));
    }
}