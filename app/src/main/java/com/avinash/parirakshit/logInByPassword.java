package com.avinash.parirakshit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class logInByPassword extends AppCompatActivity {


    long backPressedTime=0;
    @Override

    public void onBackPressed() {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {
            backPressedTime = t;
            Toast.makeText(this, "Press back again to Exit",
                    Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
            finish();
            moveTaskToBack(true);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_by_password);
        Button buttonEnterVault =findViewById(R.id.logInByPasswordButton);
        TextView buttonVerify =findViewById(R.id.forgetPasswordButton);
        ImageButton gear=findViewById(R.id.gearIconLogInByPassword);
        TextView developers =findViewById(R.id.developers);

        buttonEnterVault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputLayout textInputLayout = findViewById(R.id.logInByPasswordEditText);
                String password = textInputLayout.getEditText().getText().toString();
                SharedPreferences sharedPreferencesPassword = getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
                String savedPassword = sharedPreferencesPassword.getString("Password", null);
                SharedPreferences sharedPreferencesFakePassword=getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
                String savedFakePassword=sharedPreferencesFakePassword.getString("FakePassword",null);
                if (password.equals(savedPassword)) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
                else if(password.equals(savedFakePassword)){
                    Intent i2 = new Intent(getApplicationContext(),FakeValut.class);
                    startActivity(i2);
                }
                else {
                    textInputLayout.setError("Wrong Password!");
                }
            }
        });

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i=new Intent(getApplicationContext(),verifyUser.class);
                    startActivity(i);
            }
        });
        gear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),settings.class);
                startActivity(i);
            }
        });

        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),about.class);
                startActivity(i);
            }
        });
    }
    }

