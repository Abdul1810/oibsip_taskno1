package com.oasis.converter;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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


public class LengthActivity extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerInputUnit;
    private Spinner spinnerOutputUnit;
    private Button buttonConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        
        editTextValue = findViewById(R.id.editTextMeters);
        spinnerInputUnit = findViewById(R.id.spinnerInputUnits);
        spinnerOutputUnit = findViewById(R.id.spinnerOutputUnits);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        
        ArrayAdapter<CharSequence> inputUnitAdapter = ArrayAdapter.createFromResource(this,
                R.array.length_units, android.R.layout.simple_spinner_item);
        inputUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInputUnit.setAdapter(inputUnitAdapter);
        
        spinnerInputUnit.setSelection(0, false);
        
        ArrayAdapter<CharSequence> outputUnitAdapter = ArrayAdapter.createFromResource(this,
                R.array.length_units, android.R.layout.simple_spinner_item);
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
                    Toast.makeText(LengthActivity.this, "Please choose both input and output units", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (!valueString.isEmpty()) {
                    
                    double value = Double.parseDouble(valueString);
                    double convertedValue = convert(value, inputUnit, outputUnit);

                    
                    textViewResult.setText(String.format("%.2f %s is %.2f %s", value, inputUnit, convertedValue, outputUnit));
                } else {
                    
                    Toast.makeText(LengthActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    
    private double convert(double value, String inputUnit, String outputUnit) {
        
        if (inputUnit.equals(getString(R.string.select_unit)) || outputUnit.equals(getString(R.string.select_unit))) {
            
            textViewResult.setText("Please choose both input and output units");
            return 0; 
        }
        double conversionFactor = 1.0; 

        
        
     if (inputUnit.equals("Meters")) {
            if (outputUnit.equals("Kilometers")) {
                conversionFactor = 0.001;
            } else if (outputUnit.equals("Centimeters")) {
                conversionFactor = 100;
            } else if (outputUnit.equals("Millimeters")) {
                conversionFactor = 1000;
            } else if (outputUnit.equals("Inches")) {
                conversionFactor = 39.3701;
            } else if (outputUnit.equals("Feet")) {
                conversionFactor = 3.28084;
            } else if (outputUnit.equals("Yards")) {
                conversionFactor = 1.09361;
            }

        }
        
        else if (inputUnit.equals("Kilometers")) {
            if (outputUnit.equals("Meters")) {
                conversionFactor = 1000;
            } else if (outputUnit.equals("Centimeters")) {
                conversionFactor = 100000;
            } else if (outputUnit.equals("Millimeters")) {
                conversionFactor = 1000000;
            } else if (outputUnit.equals("Inches")) {
                conversionFactor = 39370.1;
            } else if (outputUnit.equals("Feet")) {
                conversionFactor = 3280.84;
            } else if (outputUnit.equals("Yards")) {
                conversionFactor = 1093.61;
            }
        }
        
        else if (inputUnit.equals("Centimeters")) {
            if (outputUnit.equals("Meters")) {
                conversionFactor = 0.01;
            } else if (outputUnit.equals("Kilometers")) {
                conversionFactor = 0.00001;
            } else if (outputUnit.equals("Millimeters")) {
                conversionFactor = 10;
            } else if (outputUnit.equals("Inches")) {
                conversionFactor = 0.393701;
            } else if (outputUnit.equals("Feet")) {
                conversionFactor = 0.0328084;
            } else if (outputUnit.equals("Yards")) {
                conversionFactor = 0.0109361;
            }
        }
        
        else if (inputUnit.equals("Millimeters")) {
            if (outputUnit.equals("Meters")) {
                conversionFactor = 0.001;
            } else if (outputUnit.equals("Kilometers")) {
                conversionFactor = 0.000001;
            } else if (outputUnit.equals("Centimeters")) {
                conversionFactor = 0.1;
            } else if (outputUnit.equals("Inches")) {
                conversionFactor = 0.0393701;
            } else if (outputUnit.equals("Feet")) {
                conversionFactor = 0.00328084;
            } else if (outputUnit.equals("Yards")) {
                conversionFactor = 0.00109361;
            }
        }
        
        else if (inputUnit.equals("Inches")) {
            if (outputUnit.equals("Meters")) {
                conversionFactor = 0.0254;
            } else if (outputUnit.equals("Kilometers")) {
                conversionFactor = 0.0000254;
            } else if (outputUnit.equals("Centimeters")) {
                conversionFactor = 2.54;
            } else if (outputUnit.equals("Millimeters")) {
                conversionFactor = 25.4;
            } else if (outputUnit.equals("Feet")) {
                conversionFactor = 0.0833333;
            } else if (outputUnit.equals("Yards")) {
                conversionFactor = 0.0277778;
            } else if (outputUnit.equals("Miles")) {
                conversionFactor = 0.0000157828;

            }


     }
        
        else if (inputUnit.equals("Feet")) {
            if (outputUnit.equals("Meters")) {
                conversionFactor = 0.3048;
            } else if (outputUnit.equals("Kilometers")) {
                conversionFactor = 0.0003048;
            }  else if (outputUnit.equals("Millimeters")) {
                conversionFactor = 304.8;
            } else if (outputUnit.equals("Inches")) {
                conversionFactor = 12;
            } else if (outputUnit.equals("Yards")) {
                conversionFactor = 0.333333;
            } else if (outputUnit.equals("Miles")) {
                conversionFactor = 0.000189394;
            }
     }
        
        else if (inputUnit.equals("Yards")) {
            if (outputUnit.equals("Meters")) {
                conversionFactor = 0.9144;
            } else if (outputUnit.equals("Kilometers")) {
                conversionFactor = 0.0009144;
            } else if (outputUnit.equals("Millimeters")) {
                conversionFactor = 914.4;
            } else if (outputUnit.equals("Inches")) {
                conversionFactor = 36;
            } else if (outputUnit.equals("Feet")) {
                conversionFactor = 3;
            } else if (outputUnit.equals("Miles")) {
                conversionFactor = 0.000568182;
            }
     } else if (inputUnit.equals("Miles")) {
            if (outputUnit.equals("Meters")) {
                conversionFactor = 1609.34;
            } else if (outputUnit.equals("Kilometers")) {
                conversionFactor = 1.60934;
            } else if (outputUnit.equals("Millimeters")) {
                conversionFactor = 1609340;
            } else if (outputUnit.equals("Inches")) {
                conversionFactor = 63360;
            } else if (outputUnit.equals("Feet")) {
                conversionFactor = 5280;
            } else if (outputUnit.equals("Yards")) {
                conversionFactor = 1760;
            }
     }


        
        return value * conversionFactor;
    }

    
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
}
