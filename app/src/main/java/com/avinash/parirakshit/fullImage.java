package com.avinash.parirakshit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class fullImage extends AppCompatActivity {
    ImageAdapterVault imageAdapter;
    String filePathtoRestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);


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





//        getSupportActionBar().hide();
        ImageView imageView = (ImageView)findViewById(R.id.imageViewFullScreen);
        Intent intent = getIntent();
        int i = intent.getExtras().getInt("id");
        ImageAdapterVault imageAdapter = new ImageAdapterVault(this);
//        imageView.setImageResource(imageAdapter.imageArray[i]);
        imageView.setImageBitmap(BitmapFactory.decodeFile(String.valueOf(imageAdapter.files[i])));
        filePathtoRestore=String.valueOf(imageAdapter.files[i]);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showRestoreDialog(fullImage.this, "Restore",
                        "Do you want to restore this image?", false);
                return false;
            }
        });


    }


    public void showRestoreDialog(Context context, String title, String message, Boolean status) {
        AlertDialog restoreDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(message).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //move image
                        copyImage(filePathtoRestore);
                        deleteAfterCopy(filePathtoRestore);

                        try{
                            Intent i = new Intent(getApplicationContext(), vault.class);
                            startActivity(i);
                            finish();
                        }catch (Exception e){
                            Log.d("check","ayush "+e);
                        }
                    }
                })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked

                    }


                }).show();
    }


    void deleteAfterCopy(String picturepath){
        File fdelete = new File(picturepath);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                System.out.println("file Deleted :" + picturepath);
            } else {
                System.out.println("file not Deleted :" + picturepath);
            }
        }
    }

    void copyImage(String filePathtoRestore){
        File sourceLocation= new File (filePathtoRestore);
        Long tsLong=System.currentTimeMillis()/1000;
        File targetLocation= new File ("/storage/emulated/0/"+tsLong+".jpg");
        OutputStream out = null;
        InputStream in = null;
        try {
            in = new FileInputStream(sourceLocation);
            out = new FileOutputStream(targetLocation);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("checke","ayush_notworking"+e);
        }


        // Copy the bits from instream to outstream
        byte[] buf = new byte[1024];
        int len = 0;
        while (true) {
            try {
                if (!((len = in.read(buf)) > 0)) break;
                out.write(buf, 0, len);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
