package com.avinash.parirakshit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class securityQuestion extends AppCompatActivity {
//
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
    public void securityQuestionDone(View view){
        EditText editTextQuestion=(EditText)findViewById(R.id.createSecurityQuestionTextField);
        EditText editTextAnswer=(EditText)findViewById(R.id.SecurityQuestionAnswerTextField);
        String question=editTextQuestion.getText().toString();
        String answer=editTextAnswer.getText().toString();

        SharedPreferences sharedPreferencesSavedQuestion=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferencesSavedAnswer=this.getSharedPreferences("com.avinash.parirakshit", Context.MODE_PRIVATE);
        sharedPreferencesSavedQuestion.edit().putString("Question",question).apply();
        sharedPreferencesSavedAnswer.edit().putString("Answer",answer).apply();
        String savedQuestion=sharedPreferencesSavedQuestion.getString("Question",null);
        String savedAnswer=sharedPreferencesSavedAnswer.getString("Answer",null);
        Log.i("Question",savedQuestion);
        Log.i("Answer",savedAnswer);
        Toast.makeText(getApplicationContext(),"Data Saved Successfully!!",Toast.LENGTH_SHORT).show();

        if(question.isEmpty() || answer.isEmpty()){
            editTextAnswer.setError("Can't Leave Empty");
            editTextQuestion.setError("Can't Leave Empty");
        }
        else {
            Intent i=new Intent(getApplicationContext(),logInByPassword.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question);
    }
}
