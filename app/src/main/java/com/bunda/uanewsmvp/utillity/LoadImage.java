package com.bunda.uanewsmvp.utillity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bunda.uanewsmvp.R;

public class LoadImage {
    private static String TAG = "LoadImage ";
    CircularProgressDrawable progressDrawable;

    public void loadImageInternet(View view, String uri, ImageView imageView) {
        progressDrawable = new CircularProgressDrawable(view.getContext());
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();

        Glide.with(view)
                .load(uri)
                .placeholder(progressDrawable)
                .transform(new CenterCrop(), new RoundedCorners(16))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }
}
