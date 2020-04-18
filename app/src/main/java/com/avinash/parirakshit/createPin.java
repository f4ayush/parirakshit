package com.avinash.parirakshit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class createPin extends AppCompatActivity {

//    long backPressedTime=0;
//    @Override
//    public void onBackPressed() {
//        long t = System.currentTimeMillis();
//        if (t - backPressedTime > 2000) {
//            backPressedTime = t;
//            Toast.makeText(this, "Press back again to Exit",
//                    Toast.LENGTH_SHORT).show();
//        } else {
//            super.onBackPressed();
//            finish();
//            moveTaskToBack(true);
//        }
//    }
        public void nextActivity(View view){
            Intent i = new Intent(getApplicationContext(), createFakeVaultPassword.class);
            TextInputLayout Pin=(TextInputLayout)findViewById(R.id.createPinEditText);
            TextInputLayout confPin=(TextInputLayout)findViewById(R.id.confirmPinEditText);
            String pinn=Pin.getEditText().getText().toString();
            String confirmPinn=confPin.getEditText().getText().toString();
            try {
                int pin = Integer.parseInt(pinn);
                int confirmPin = Integer.parseInt(confirmPinn);
            }catch (NumberFormatException e){}
            if(pinn.isEmpty() || confirmPinn.isEmpty()){
                    Pin.setError("Can't leave fields empty");
                    confPin.setError("Can't leave fields empty");
            }
            else if(pinn.equals(confirmPinn) && pinn.length()>3 && pinn.length()<=10) {
                startActivity(i);
                SharedPreferences sharedPreferencesPin=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
                sharedPreferencesPin.edit().putString("Pin",confirmPinn).apply();
                String savedPin=sharedPreferencesPin.getString("Pin",null);
                Log.i("Pin",savedPin);
            }
            else if(pinn.isEmpty() || confirmPinn.isEmpty()){
                Pin.setError("Can't leave fields empty!");
                confPin.setError("Can't leave fields empty!");
            }
            else if(pinn.length()<4 || pinn.length()>10){
                Pin.setError("Pin must be between 4-10 digits");
            }
            else{
                confPin.setError("Not Matched!");
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pin);
    }
}
