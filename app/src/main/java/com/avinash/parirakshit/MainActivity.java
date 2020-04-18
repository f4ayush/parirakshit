package com.avinash.parirakshit;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_WRITE=0;
    private static final int PERMISSION_REQUEST_READ=0;
    private static final int PERMISSION_REQUEST=0;
    private static final int RESULT_LOAD_IMAGE=0;
    private String selectedImagePath;
    File myDirectory;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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




        //Other inmplementation



        Button openGalleryButton = findViewById(R.id.openGalleryButton);
        Button openVaultButton= findViewById(R.id.openVaultButton);

        ///seek permission of reading and writing
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_READ);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_WRITE);
        }

        //OPENS GALLERY
        openGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });

        //OPENS Vault
        openVaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), vault.class);
                startActivity(i);
            }
        });



    }




    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case PERMISSION_REQUEST:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"Permission not granted",Toast.LENGTH_SHORT).show();
                }
        }
    }



    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case RESULT_LOAD_IMAGE:
                if (resultCode==RESULT_OK){
                    Uri selectedImage= data.getData();
                    String[] filePathColumn={MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    Log.d("checkPath",picturePath+"ayush");

                    Uri uri = Uri.parse(picturePath);
                    File sourceLocation= new File (picturePath);
                    Long tsLong=System.currentTimeMillis()/1000;
                    File targetLocation= new File ("/storage/emulated/0/hope/"+tsLong);
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

                    deleteAfterCopy(picturePath);
//                    imageView.setImageBitmap(BitmapFactory.decodeFile(String.valueOf(targetLocation)));

                } else {
                    Toast.makeText(this,"Permission not granted",Toast.LENGTH_SHORT).show();
                }
        }
    }


    void deleteAfterCopy(String picturepath){
//        ContentResolver contentResolver = getContentResolver();
//        contentResolver.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                MediaStore.Images.ImageColumns.DATA + "=?" , new String[]{ picturepath });
        File fdelete = new File(picturepath);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                System.out.println("file Deleted :" + picturepath);
            } else {
                System.out.println("file not Deleted :" + picturepath);
            }
        }
    }

}
