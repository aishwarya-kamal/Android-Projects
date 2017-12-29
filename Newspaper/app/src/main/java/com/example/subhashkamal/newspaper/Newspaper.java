package com.example.subhashkamal.newspaper;

/**
 * Created by Jose Marrima on 2/11/2017.
 */

public class Newspaper {
    /* This News Class represents a single news article */

    private String title; // this is the title of the article
    private String description; // this is the body content of the article
    private String imageUrl; // this is the image url for that particular news article

    /*
        *This creates the object named news that will host a single news article
        * @param title represents the title of the news article
        * @param description represents the news article body
        * @param imageUrl represent the image associated with the news article
     */

    public Newspaper(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    //Gets the title of the news article
    public String getTitle() {
        return title;
    }

    //gets the main body content of the news article
    public String getDescription() {
        return description;
    }

    // gets the image url associated with that news article
    public String getImageUrl() {
        return imageUrl;
    }

}
