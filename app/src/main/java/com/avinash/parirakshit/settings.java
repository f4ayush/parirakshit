package com.avinash.parirakshit;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static com.avinash.parirakshit.R.id;
import static com.avinash.parirakshit.R.layout;
public class settings extends AppCompatActivity {
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_settings);

        //Flip to close implementation

        final SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        final Sensor sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        final SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if(sensorEvent.values[0]<sensorProximity.getMaximumRange()){
                    System.exit(0);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(sensorEventListener,sensorProximity,2*1000*1000);


        //Other Implementations




        final Spinner spinner=findViewById(id.spinner);
        final List<String> input=new ArrayList<>();
        input.add("Password");
        input.add("Pin");
        final SharedPreferences sharedPreferencesSelected=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String >(getApplicationContext(),android.R.layout.simple_dropdown_item_1line, input);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Integer inputType=0;
                String selected= (String) spinner.getSelectedItem().toString();
                if(selected.equals("Password")){
                    inputType=0;
                }else if(selected.equals("Pin")){
                    inputType=1;
                }
                sharedPreferencesSelected.edit().putInt("selectedItem",inputType).apply();
                Integer selectedItem=sharedPreferencesSelected.getInt("selectedItem",0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
