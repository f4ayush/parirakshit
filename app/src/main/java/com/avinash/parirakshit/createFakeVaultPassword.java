package com.avinash.parirakshit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class createFakeVaultPassword extends AppCompatActivity {



    public void nextActivity(View view){
        int launchCount=0;
        Intent i = new Intent(getApplicationContext(), securityQuestion.class);
        TextInputLayout pass=(TextInputLayout)findViewById(R.id.createFakePasswordEditText);
        TextInputLayout confirmPass=(TextInputLayout)findViewById(R.id.confirmFakePasswordEditText);
        String fakepassword=pass.getEditText().getText().toString();
        String confirmfakePassword=confirmPass.getEditText().getText().toString();
        if(fakepassword.isEmpty() && confirmfakePassword.isEmpty()){
            pass.setError("Can't leave fields empty!");
            confirmPass.setError("Can't leave fields empty!");
        }
        else if(fakepassword.length()<6){
            pass.setError("Too Short (more than 5)");
        }
        else if(fakepassword.equals(confirmfakePassword) && fakepassword.length()>=6) {
            startActivity(i);
            launchCount=launchCount+1;
            SharedPreferences sharedPreferencesFakePassword=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
            sharedPreferencesFakePassword.edit().putString("FakePassword",confirmfakePassword).apply();
            String savedFakePassword=sharedPreferencesFakePassword.getString("FakePassword",null);
            SharedPreferences launchCounter=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
            launchCounter.edit().putInt("launchCount",launchCount).apply();
            Integer launchCounting= launchCounter.getInt("launchCount",0);
            Log.i("FakePassword",savedFakePassword);
        }
        else{
            confirmPass.setError("Not Matched!");
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fake_vault_password);
    }
}
