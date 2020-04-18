package com.avinash.parirakshit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class forgetPassword extends AppCompatActivity {

    public void savePassword(View view){
        TextInputLayout textInputLayout=(TextInputLayout)findViewById(R.id.forgetPasswordEditText);
        String newPassword=textInputLayout.getEditText().getText().toString();

        if(newPassword.isEmpty()){
            textInputLayout.setError("Can't leave field empty!");
        }
        else if(newPassword.length()<6){
            textInputLayout.setError("Too Short (more than 5)");
        }
        else{
            SharedPreferences sharedPreferencesPassword=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
            sharedPreferencesPassword.edit().putString("Password",newPassword).apply();
            String savedPassword=sharedPreferencesPassword.getString("Password",null);
            Toast.makeText(getApplicationContext(),"Password changed successfully!!",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(),logInByPassword.class);
            startActivity(i);
            Log.i("Password",savedPassword);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


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

    }
}
