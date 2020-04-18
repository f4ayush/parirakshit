package com.avinash.parirakshit;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class vault extends AppCompatActivity {

    GridView imageGridView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);


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



        //GridView implementation

        imageGridView=(GridView)findViewById(R.id.imageGridView);
        imageGridView.setAdapter(new ImageAdapterVault(this));


        imageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),fullImage.class);
                intent.putExtra("id",i);
                startActivity(intent);
                finish();


            }
        });

    }

}
