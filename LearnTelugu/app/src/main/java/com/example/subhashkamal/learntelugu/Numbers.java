package com.example.subhashkamal.learntelugu;

public class Numbers {

    // my default language
    private String mDefaultLanguage;

    // for telugu
    private String mTeluguLamguage;

    // Image's Id for a particular image to be displayed
    private int mImageResourceId;

    // Audio's id for the audio to be played
    private int mAudioResourceId;

    //
    public Numbers(String defaultLanguage, String teluguLanguage, int imageResourceId, int audioResourceId){
        mDefaultLanguage = defaultLanguage;
        mTeluguLamguage = teluguLanguage;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    //getters
    public String getDefaultLanguage() {
        return mDefaultLanguage;
    }

    public String getTeluguLamguage() {
        return mTeluguLamguage;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getAudioResourceId() { return mAudioResourceId; }
}