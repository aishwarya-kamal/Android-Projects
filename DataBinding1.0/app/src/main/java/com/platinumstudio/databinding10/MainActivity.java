package com.platinumstudio.databinding10;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.platinumstudio.databinding10.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.recyclerView.setHasFixedSize(true);

        adapter = new CustomAdapter(prepareUser(), this);
        mainBinding.recyclerView.setAdapter(adapter);
    }

    private List<User> prepareUser(){
        List<String> names = Arrays.asList(getResources().getStringArray(R.array.names));
        int[] imageId = {R.drawable.apple, R.drawable.mango, R.drawable.strawberry, R.drawable.banana,
                R.drawable.watermelon, R.drawable.orange, R.drawable.cherry, R.drawable.apple, R.drawable.mango, R.drawable.strawberry, R.drawable.banana,
                R.drawable.watermelon, R.drawable.orange, R.drawable.cherry,};

        List<User> Names = new ArrayList<>();
        int count = 0;
        for(String name : names){
            Names.add(new User(name, imageId[count]));
            count ++;
        }
        return Names;

    }

}
