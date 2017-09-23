package com.example.subhashkamal.learntelugu;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersAdapter extends ArrayAdapter {

    public NumbersAdapter (Activity context, ArrayList<Numbers> numbers){
        super(context, 0, numbers);
    }

    /*
    Provides a view for an AdapterView (ListView, GridView, etc.)
    @param position
    returns a list item view
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Numbers currentNumber = (Numbers) getItem(position);

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_language);
        defaultTextView.setText(currentNumber.getDefaultLanguage());

        TextView teluguTextView = (TextView) listItemView.findViewById(R.id.telugu_language);
        teluguTextView.setText(currentNumber.getTeluguLamguage() );

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentNumber.getImageResourceId());

        return listItemView;
    }
}
