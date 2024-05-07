package com.oasis.converter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TempActivity extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerInputUnit;
    private Spinner spinnerOutputUnit;
    private Button buttonConvert;
    private TextView textViewResult;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editTextValue = findViewById(R.id.editTextValue);
        spinnerInputUnit = findViewById(R.id.spinnerInputUnits);
        spinnerOutputUnit = findViewById(R.id.spinnerOutputUnits);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> inputUnitAdapter = ArrayAdapter.createFromResource(this,
                R.array.temperature_units, android.R.layout.simple_spinner_item);
        inputUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInputUnit.setAdapter(inputUnitAdapter);
        spinnerInputUnit.setSelection(0, false); // Select hint text item without triggering onItemSelected listener

        ArrayAdapter<CharSequence> outputUnitAdapter = ArrayAdapter.createFromResource(this,
                R.array.temperature_units, android.R.layout.simple_spinner_item);
        outputUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOutputUnit.setAdapter(outputUnitAdapter);
        spinnerOutputUnit.setSelection(0, false); // Select hint text item without triggering onItemSelected listener

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueString = editTextValue.getText().toString();

                String inputUnit = spinnerInputUnit.getSelectedItem().toString();
                String outputUnit = spinnerOutputUnit.getSelectedItem().toString();

                if (inputUnit.equals("Select Unit") || outputUnit.equals("Select Unit")) {
                    // create a toast message
                    Toast.makeText(TempActivity.this, "Please choose both input and output units", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!valueString.isEmpty()) {
                    double value = Double.parseDouble(valueString);


                    double convertedValue = convert(value, inputUnit, outputUnit);

                    textViewResult.setText(String.format("%.2f %s Is %.2f %s", value, inputUnit, convertedValue, outputUnit));
                } else {
                    Toast.makeText(TempActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private double convert(double value, String inputUnit, String outputUnit) {
        if (inputUnit.equals("Celsius") && outputUnit.equals("Fahrenheit")) {
            return (value * 9 / 5) + 32;
        } else if (inputUnit.equals("Celsius") && outputUnit.equals("Kelvin")) {
            return value + 273.15;
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Celsius")) {
            return (value - 32) * 5 / 9;
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Kelvin")) {
            return (value - 32) * 5 / 9 + 273.15;
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Celsius")) {
            return value - 273.15;
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Fahrenheit")) {
            return (value - 273.15) * 9 / 5 + 32;
        } else {
            return value;
        }
    }
}
