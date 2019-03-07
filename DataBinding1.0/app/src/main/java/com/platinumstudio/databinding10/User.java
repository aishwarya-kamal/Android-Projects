package com.platinumstudio.databinding10;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class User {
    public String name;
    public int imageId;

    public User(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

//    @BindingAdapter({"android:src"})
//    public static void loadImage(View view, int imageId){
//        CircleImageView circleImageView = (CircleImageView) view;
//        circleImageView.setImageDrawable(ContextCompat.getDrawable(view.getContext(), imageId));
//    }

    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView imageView, int imageId){
        imageView.setImageResource(imageId);
    }

}
