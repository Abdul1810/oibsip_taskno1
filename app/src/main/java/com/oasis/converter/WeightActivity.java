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

public class WeightActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_weight);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editTextValue = findViewById(R.id.editTextKilograms);
        spinnerInputUnit = findViewById(R.id.spinnerInputUnits);
        spinnerOutputUnit = findViewById(R.id.spinnerOutputUnits);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> inputUnitAdapter = ArrayAdapter.createFromResource(this,
                R.array.weight_units, android.R.layout.simple_spinner_item);
        inputUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInputUnit.setAdapter(inputUnitAdapter);
        spinnerInputUnit.setSelection(0, false);        ArrayAdapter<CharSequence> outputUnitAdapter = ArrayAdapter.createFromResource(this,
                R.array.weight_units, android.R.layout.simple_spinner_item);
        outputUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOutputUnit.setAdapter(outputUnitAdapter);
        spinnerOutputUnit.setSelection(0, false);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueString = editTextValue.getText().toString();

                String inputUnit = spinnerInputUnit.getSelectedItem().toString();
                String outputUnit = spinnerOutputUnit.getSelectedItem().toString();

                if (inputUnit.equals("Select Unit") || outputUnit.equals("Select Unit")) {
                    // create a toast message
                    Toast.makeText(WeightActivity.this, "Please choose both input and output units", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!valueString.isEmpty()) {
                    double value = Double.parseDouble(valueString);


                    double convertedValue = convert(value, inputUnit, outputUnit);

                    textViewResult.setText(String.format("%.2f %s is %.2f %s", value, inputUnit, convertedValue, outputUnit));
                } else {
                    Toast.makeText(WeightActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private double convert(double value, String inputUnit, String outputUnit) {
        if (inputUnit.equals(getString(R.string.select_unit)) || outputUnit.equals(getString(R.string.select_unit))) {
            textViewResult.setText("Please choose both input and output units");
        }

        double conversionFactor = 1.0;        if (inputUnit.equals("Milligrams")) {
            if (outputUnit.equals("Kilograms")) {
                conversionFactor = 1e-6;
            } else if (outputUnit.equals("Grams")) {
                conversionFactor = 1e-3;
            } else if (outputUnit.equals("Pounds")) {
                conversionFactor = 2.20462e-6;
            } else if (outputUnit.equals("Ounces")) {
                conversionFactor = 3.5274e-5;
            } else if (outputUnit.equals("Tons")) {
                conversionFactor = 1.10231e-9;
            }
        } else if (inputUnit.equals("Kilograms")) {
            if (outputUnit.equals("Milligrams")) {
                conversionFactor = 1e6;
            } else if (outputUnit.equals("Grams")) {
                conversionFactor = 1000;
            } else if (outputUnit.equals("Pounds")) {
                conversionFactor = 2.20462;
            } else if (outputUnit.equals("Ounces")) {
                conversionFactor = 35.274;
            } else if (outputUnit.equals("Tons")) {
                conversionFactor = 0.001;
            }
        } else if (inputUnit.equals("Grams")) {
            if (outputUnit.equals("Milligrams")) {
                conversionFactor = 1000;
            } else if (outputUnit.equals("Kilograms")) {
                conversionFactor = 0.001;
            } else if (outputUnit.equals("Pounds")) {
                conversionFactor = 0.00220462;
            } else if (outputUnit.equals("Ounces")) {
                conversionFactor = 0.035274;
            } else if (outputUnit.equals("Tons")) {
                conversionFactor = 1.0e-6;
            }
        } else if (inputUnit.equals("Pounds")) {
            if (outputUnit.equals("Milligrams")) {
                conversionFactor = 453592;
            } else if (outputUnit.equals("Kilograms")) {
                conversionFactor = 0.453592;
            } else if (outputUnit.equals("Grams")) {
                conversionFactor = 453.592;
            } else if (outputUnit.equals("Ounces")) {
                conversionFactor = 16;
            } else if (outputUnit.equals("Tons")) {
                conversionFactor = 0.0005;
            }
        } else if (inputUnit.equals("Ounces")) {
            if (outputUnit.equals("Milligrams")) {
                conversionFactor = 28349.5;
            } else if (outputUnit.equals("Kilograms")) {
                conversionFactor = 0.0283495;
            } else if (outputUnit.equals("Grams")) {
                conversionFactor = 28.3495;
            } else if (outputUnit.equals("Pounds")) {
                conversionFactor = 0.0625;
            } else if (outputUnit.equals("Tons")) {
                conversionFactor = 3.125e-5;
            }
        } else if (inputUnit.equals("Tons")) {
            if (outputUnit.equals("Milligrams")) {
                conversionFactor = 1.0e+9;
            } else if (outputUnit.equals("Kilograms")) {
                conversionFactor = 1000;
            } else if (outputUnit.equals("Grams")) {
                conversionFactor = 1.0e+6;
            } else if (outputUnit.equals("Pounds")) {
                conversionFactor = 2204.62;
            } else if (outputUnit.equals("Ounces")) {
                conversionFactor = 35274;
            }
        }

        return value * conversionFactor;
    }
}
