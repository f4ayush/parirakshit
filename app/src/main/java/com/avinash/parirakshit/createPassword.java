package com.avinash.parirakshit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.preference.PreferenceManager.*;


public class createPassword extends AppCompatActivity {
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
        int launchCount=0;
        Intent i = new Intent(getApplicationContext(), createPin.class);
        TextInputLayout pass=(TextInputLayout)findViewById(R.id.createPasswordEditText);
        TextInputLayout confirmPass=(TextInputLayout)findViewById(R.id.confirmPasswordEditText);
        String password=pass.getEditText().getText().toString();
        String confirmPassword=confirmPass.getEditText().getText().toString();
        if(password.isEmpty() && confirmPassword.isEmpty()){
            pass.setError("Can't leave fields empty!");
            confirmPass.setError("Can't leave fields empty!");
        }
        else if(password.length()<6){
            pass.setError("Too Short (more than 5)");
        }
        else if(password.equals(confirmPassword) && password.length()>=6) {
            startActivity(i);
            launchCount=launchCount+1;
            SharedPreferences sharedPreferencesPassword=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
            sharedPreferencesPassword.edit().putString("Password",confirmPassword).apply();
            String savedPassword=sharedPreferencesPassword.getString("Password",null);
            SharedPreferences launchCounter=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
            launchCounter.edit().putInt("launchCount",launchCount).apply();
            Integer launchCounting= launchCounter.getInt("launchCount",0);
            Log.i("Password",savedPassword);
        }
        else{
            confirmPass.setError("Not Matched!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
    }
}
