package com.avinash.parirakshit;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;

public class ImageAdapterVault extends BaseAdapter {

    private Context mContext;
//    public int[] imageArray = {
//
//            //Put images in the image array
//
//
//    };

    String path = Environment.getExternalStorageDirectory().toString()+"/hope";
    File directory = new File(path);
    File[] files = directory.listFiles();


    public ImageAdapterVault(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public Object getItem(int i) {
        return files[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override

    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageView = new ImageView(mContext);
//        imageView.setImageResource(imageArray[i]);
        imageView.setImageBitmap(BitmapFactory.decodeFile(String.valueOf(files[i])));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340,350));
        return imageView;
    }
}

