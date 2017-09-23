package com.example.android.quakereport;

public class Earthquake {

    // Declaring variables to store magnitude, date, time, primary location,
    // offset, and url of the list of Earthquakes

    private String mMagnitude;

    private String mDate;

    private String mTime;

    private String mPrimaryLocation;

    private String mOffset;

    private String mUrl;

    // Create an Earthquake constructor
    public Earthquake(String mMagnitude, String mOffset, String mPrimaryLocation, String mDate, String mTime, String mUrl){
        this.mMagnitude = mMagnitude;
        this.mPrimaryLocation = mPrimaryLocation;
        this.mOffset = mOffset;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mUrl = mUrl;
    }

    /** This method returns the magnitude of the Earthquake */
    public String getmMagnitude() {
        return mMagnitude;
    }

    /** This method returns the primary location of the Earthquake */
    public String getmPrimaryLocation() {
        return mPrimaryLocation;
    }

    /** This method returns the offset of the Earthquake */
    public String getmOffset() { return mOffset;}

    /** This method returns the time of the Earthquake */
    public String getmTime() {  return mTime; }

    /** This method returns the date of the Earthquake */
    public String getmDate() {  return mDate; }

    /* This method returns the URL of the website to find more information about the earthquake. */
    public String getmUrl() {  return mUrl;  }
}