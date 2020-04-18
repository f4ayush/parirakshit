package com.avinash.parirakshit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    private static int splashTimeOut=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setContentView(R.layout.activity_splash);
        final SharedPreferences launchCounter=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
        final SharedPreferences sharedPreferencesSelected=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
        final Integer selectedItem=sharedPreferencesSelected.getInt("selectedItem",0);
        final Integer launchCounting= launchCounter.getInt("launchCount",0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                if(launchCounting==0) {
                    Intent i = new Intent(Splash.this, createPassword.class);
                    startActivity(i);
                    finish();
                }
                else if(selectedItem.equals(0)){
                    Intent i = new Intent(getApplicationContext(), logInByPassword.class);
                    startActivity(i);
                    finish();
                }
                else if(selectedItem.equals(1)){
                    Intent i = new Intent(getApplicationContext(), logInByPin.class);
                    startActivity(i);
                    finish();
                }
            }
        },splashTimeOut);
    }
}
