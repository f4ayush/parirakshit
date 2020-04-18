package com.avinash.parirakshit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class logInByPin extends AppCompatActivity {

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
        setContentView(R.layout.activity_log_in_by_pin);

        final TextInputLayout textInputLayout=findViewById(R.id.logInByPinEditText);
        Button buttonEnterVault =findViewById(R.id.logInByPinButton);
        TextView buttonVerify =findViewById(R.id.forgetPinbutton);
        TextView developers =findViewById(R.id.developers1);
        ImageButton gear=findViewById(R.id.gearIconLogInByPin);
        final SharedPreferences sharedPreferencesPassword=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
        buttonEnterVault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin=textInputLayout.getEditText().getText().toString();
                String savedPin=sharedPreferencesPassword.getString("Pin",null);
                Log.i("Pin",savedPin);

        if(pin.equals(savedPin)){
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        else {
            textInputLayout.setError("Wrong Pin!");
        }
    }

        });

        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),about.class);
                startActivity(i);
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
    }
}
