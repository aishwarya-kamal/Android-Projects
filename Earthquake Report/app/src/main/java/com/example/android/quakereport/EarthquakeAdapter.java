package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.android.quakereport.R.id.magnitudeTextView;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    // Create a custom constructor & inflates the layout file
    public EarthquakeAdapter(Context context, List<Earthquake> arrayListEarthquake){
        super (context, 0, arrayListEarthquake);
    }

    @Override
    public View getView(int position, View convertView , ViewGroup parent){

    //check if the existing view is being reused or else create/inflate a new one
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_of_earthquakes, parent, false);
        }

        // get the current earthquake object from this position
        Earthquake currentEarthquake = getItem(position);

        // Find the magnitude TextView in the list_of_earthquakes.xml layout with the ID magnitudeTextView
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitudeTextView);
        magnitudeTextView.setText(currentEarthquake.getmMagnitude()); //Set this text on magnitudeTextView

        // Find the primary location in the list_of_earthquakes.xml layout with the ID locationTextView
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primaryLocationTextView);
        primaryLocationTextView.setText(currentEarthquake.getmPrimaryLocation()); //Set this text on locationTextView

        // Find the offset in the list_of_earthquakes.xml layout with the ID locationTextView
        TextView offsetLocationTextView = (TextView) listItemView.findViewById(R.id.offsetTextView);
        offsetLocationTextView.setText(currentEarthquake.getmOffset()); //Set this text on locationTextView

        // Find the text date in the list_of_earthquakes.xml layout with the ID timeTextView
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.dateTextView);
        dateTextView.setText(currentEarthquake.getmDate()); //Set this text on dateTextView

        // Find the time TextView in the list_of_earthquakes.xml layout with the ID timeTextView
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.timeTextView);
        timeTextView.setText(currentEarthquake.getmTime()); //Set this text on timeTextView

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    private int getMagnitudeColor(String magnitude) {
        Double temp = Double.parseDouble(magnitude);
        int mag = temp.intValue();
        int colour = 0;

        if (mag < 2) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude1);
        } else if (mag >= 2 && mag <= 3) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude2);
        } else if (mag >= 3 && mag <= 4) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude3);
        } else if (mag >= 4 && mag <= 5) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude4);
        } else if (mag >= 5 && mag <= 6) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude5);
        } else if (mag >= 6 && mag <= 7) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude6);
        } else if (mag >= 7 && mag <= 8) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude7);
        } else if (mag >= 8 && mag <= 9) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude8);
        } else if (mag >= 9 && mag <= 10) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude9);
        } else if (mag >= 10) {
            colour = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
        return colour;
    }
}
