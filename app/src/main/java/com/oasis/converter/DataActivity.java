package com.oasis.converter;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editTextValue = findViewById(R.id.editTextValue);
        spinnerInputUnit = findViewById(R.id.spinnerInputUnits);
        spinnerOutputUnit = findViewById(R.id.spinnerOutputUnits);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> inputUnitAdapter = ArrayAdapter.createFromResource(this,
                R.array.data_units, android.R.layout.simple_spinner_item);
        inputUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInputUnit.setAdapter(inputUnitAdapter);
        spinnerInputUnit.setSelection(0, false); // Select hint text item without triggering onItemSelected listener

        ArrayAdapter<CharSequence> outputUnitAdapter = ArrayAdapter.createFromResource(this,
                R.array.data_units, android.R.layout.simple_spinner_item);
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
                    Toast.makeText(DataActivity.this, "Please choose both input and output units", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!valueString.isEmpty()) {
                    double value = Double.parseDouble(valueString);


                    double convertedValue = convert(value, inputUnit, outputUnit);

                    textViewResult.setText(String.format("%.2f %s is %.2f %s", value, inputUnit, convertedValue, outputUnit));
                } else {
                    Toast.makeText(DataActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private double convert(double value, String inputUnit, String outputUnit) {
        if (inputUnit.equals(getString(R.string.select_unit)) || outputUnit.equals(getString(R.string.select_unit))) {
            textViewResult.setText("Please choose both input and output units");
        }

        double conversionFactor = 1.0; // Default value if same units are selected
        if (inputUnit.equals("Bits")) {
            if (outputUnit.equals("Bytes")) {
                conversionFactor = 0.125;
            } else if (outputUnit.equals("Kilobytes")) {
                conversionFactor = 1.0 / 8000.0;
            } else if (outputUnit.equals("Megabytes")) {
                conversionFactor = 1.0 / 8e+6;
            } else if (outputUnit.equals("Gigabytes")) {
                conversionFactor = 1.0 / 8e+9;
            } else if (outputUnit.equals("Terabytes")) {
                conversionFactor = 1.0 / 8e+12;
            }
        } else if (inputUnit.equals("Bytes")) {
            if (outputUnit.equals("Bits")) {
                conversionFactor = 8.0;
            } else if (outputUnit.equals("Kilobytes")) {
                conversionFactor = 1.0 / 1024.0;
            } else if (outputUnit.equals("Megabytes")) {
                conversionFactor = 1.0 / 1e+6;
            } else if (outputUnit.equals("Gigabytes")) {
                conversionFactor = 1.0 / 1e+9;
            } else if (outputUnit.equals("Terabytes")) {
                conversionFactor = 1.0 / 1e+12;
            }
        } else if (inputUnit.equals("Kilobytes")) {
            if (outputUnit.equals("Bits")) {
                conversionFactor = 8000.0;
            } else if (outputUnit.equals("Bytes")) {
                conversionFactor = 1024.0;
            } else if (outputUnit.equals("Megabytes")) {
                conversionFactor = 1.0 / 1024.0;
            } else if (outputUnit.equals("Gigabytes")) {
                conversionFactor = 1.0 / 1e+6;
            } else if (outputUnit.equals("Terabytes")) {
                conversionFactor = 1.0 / 1e+9;
            }
        } else if (inputUnit.equals("Megabytes")) {
            if (outputUnit.equals("Bits")) {
                conversionFactor = 8e+6;
            } else if (outputUnit.equals("Bytes")) {
                conversionFactor = 1e+6;
            } else if (outputUnit.equals("Kilobytes")) {
                conversionFactor = 1024.0;
            } else if (outputUnit.equals("Gigabytes")) {
                conversionFactor = 1.0 / 1024.0;
            } else if (outputUnit.equals("Terabytes")) {
                conversionFactor = 1.0 / 1e+6;
            }
        } else if (inputUnit.equals("Gigabytes")) {
            if (outputUnit.equals("Bits")) {
                conversionFactor = 8e+9;
            } else if (outputUnit.equals("Bytes")) {
                conversionFactor = 1e+9;
            } else if (outputUnit.equals("Kilobytes")) {
                conversionFactor = 1e+6;
            } else if (outputUnit.equals("Megabytes")) {
                conversionFactor = 1024.0;
            } else if (outputUnit.equals("Terabytes")) {
                conversionFactor = 1.0 / 1024.0;
            }
        } else if (inputUnit.equals("Terabytes")) {
            if (outputUnit.equals("Bits")) {
                conversionFactor = 8e+12;
            } else if (outputUnit.equals("Bytes")) {
                conversionFactor = 1e+12;
            } else if (outputUnit.equals("Kilobytes")) {
                conversionFactor = 1e+9;
            } else if (outputUnit.equals("Megabytes")) {
                conversionFactor = 1e+6;
            } else if (outputUnit.equals("Gigabytes")) {
                conversionFactor = 1024.0;
            }
        }
        return value * conversionFactor;
    }
}
// Perform conversio