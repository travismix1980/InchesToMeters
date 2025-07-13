package com.example.inchestometers;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText inchesText;
    private Button calculateButton;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // get all our views so we can modify them
        findViews();

        // setup and run our button click listener
        setupButtonClickListener();
    }

    private void findViews() {
        inchesText = findViewById(R.id.inches_edit_text);
        calculateButton = findViewById(R.id.calculate_button);
        outputText = findViewById(R.id.meters_output);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(v -> {

            outputText.setText("");

            if (inchesText.getText().toString().isEmpty()) {
                final String emptyInchesText = "Inches must be filled out";
                Toast.makeText(this, emptyInchesText, Toast.LENGTH_LONG)
                        .show();
            } else {
                double inches = Double.parseDouble(inchesText.getText().toString());
                double meters = convertInchesToMeters(inches);
                displayOutput(meters);
            }
        });
    }

    private double convertInchesToMeters(double inches) {
        return inches / 39.37;
    }

    private void displayOutput(double meters) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String meterTextResult = myDecimalFormatter.format(meters);
        String fullResultString = inchesText.getText().toString() + " inches is " +
                meterTextResult + " meters";
        outputText.setText(fullResultString);
    }
}