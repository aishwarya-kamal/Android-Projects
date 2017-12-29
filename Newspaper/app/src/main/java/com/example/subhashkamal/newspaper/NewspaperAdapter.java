package com.example.subhashkamal.newspaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jose Marrima on 2/11/2017.
 */

public class NewspaperAdapter extends ArrayAdapter<Newspaper> {

    public NewspaperAdapter(Context context, ArrayList<Newspaper> newspaper) {
        super(context, 0, newspaper);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;

        ///check of the listViewItem is inflated, if not inflate
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.news_item,
                    parent, false);
        }

        // get the current news article position
        Newspaper currentNewspaper = getItem(position);

        // Find the textview for the title
        TextView titleTextView = listViewItem.findViewById(R.id.news_title);
        //set the title to the titleTextView
        titleTextView.setText(currentNewspaper.getTitle());

        // Find the textview for the description
        TextView descriptionTextView = listViewItem.findViewById(R.id.news_description);
        //set the title to the descriptionTextView
        descriptionTextView.setText(currentNewspaper.getDescription());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.news_image);
        // Display the image from the url on to the ImageView
        Picasso.with(getContext()).load(currentNewspaper.getImageUrl()).into(imageView);


        return listViewItem;
    }
}
