package com.example.android.quakereport;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Create Utility class with the methods for performing the HTTP request and parse the response.
public final class QueryUtils {

    // Create a tag for log messages
    private static final String LOG_TAG = QueryUtils.class.getName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /** Helper methods related to requesting and receiving earthquake data from USGS.  */

    // Query the USGS dataset and return an {@link Event} object to represent a single earthquake.
    public static List<Earthquake> fetchEarthquakeData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        List<Earthquake> earthquakes = extractFeatureFromJson(jsonResponse);

        // Return the list of earthquakes
        return earthquakes;
    }

    // Creates and returns new URL object from the given string URL.
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    // Make a HTTP request to the URL and return the response as a string.
    private static String makeHttpRequest(URL url) throws IOException {
        // Declare and initialize an empty String for the json response
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            // Setting up the HTTP request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET"); // HTTP method request is GET because we want to get the information

            // Actually establishing the HTTP connection with the server
            urlConnection.connect();

            // Receiving the response and making sense i.e., If the request was successful (response
            // code 200), then read the input stream and parse the response(extract the json response)
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    // Converting inputstream into String that has the json response from the server
    private static String readFromStream(InputStream inputStream) throws IOException {
        // Create a new String builder
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            // Append all the text lines available in the buffered reader
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            // Setup the builder
            BufferedReader reader = new BufferedReader(inputStreamReader);
            // Ask buffered reader for a line of text
            String line = reader.readLine();
            while (line != null) {
                // Append that line to the String buffer
                output.append(line);
                // Read another line
                line = reader.readLine();
            }
        }
        // Use toString method to get and return the final output from the StringBuilder
        return output.toString();
    }

    // Return a list of {@link Earthquake} objects that has been built up from parsing a JSON response.
    private static List<Earthquake> extractFeatureFromJson(String earthquakeJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();

        /** Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        *  is formatted, a JSONException exception object will be thrown.
        * Catch the exception so the app doesn't crash, and print the error message to the logs. */
        try {
            // Create new root json object by calling the constructor and pass earthquakeJSON
            JSONObject rootJsonObject = new JSONObject(earthquakeJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray earthquakeArray = rootJsonObject.getJSONArray("features");

            for(int i = 0; i < earthquakeArray.length(); i++){

                // Get a single earthquake at position i within the list of earthquakes
                JSONObject earthquakeJsonObj = earthquakeArray.getJSONObject(i);
                // For a given earthquake, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.
                JSONObject propertiesJsonObject = earthquakeJsonObj.getJSONObject("properties");
                // Extract the value for the key called "mag", "place", "time", "url"
                double magnitude = propertiesJsonObject.getDouble("mag");
                String location = propertiesJsonObject.getString("place");
                long time = propertiesJsonObject.getLong("time");
                String url = propertiesJsonObject.getString("url");

                DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
                String magnitudeToDisplay = magnitudeFormatter.format(magnitude);

                String primaryLocation;
                String offset;

                if(location.contains("of") == true){
                    String[] separated = location.split("of");
                    offset = separated[0] + "of";
                    primaryLocation = separated[1].trim();
                } else {
                    offset = "Near the";
                    primaryLocation = location;
                }

                Date dateObject = new Date(time);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM d, yyy");
                String dateToDisplay = dateFormatter.format(dateObject);

                SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
                String timeToDisplay = timeFormatter.format(dateObject);

                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response
                Earthquake earthquakeJavaObject = new Earthquake(magnitudeToDisplay, offset, primaryLocation, dateToDisplay, timeToDisplay, url);
                earthquakes.add(earthquakeJavaObject);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}