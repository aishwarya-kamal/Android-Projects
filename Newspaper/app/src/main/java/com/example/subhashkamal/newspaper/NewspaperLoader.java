package com.example.subhashkamal.newspaper;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Jose Marrima on 4/11/2017.
 */

//android.support.v4.content.AsyncTaskLoader<D>

public class NewspaperLoader extends android.support.v4.content.AsyncTaskLoader<List<Newspaper>> {

    /** Query URL */
    private String url;

    public NewspaperLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Newspaper> loadInBackground() {
        if (url == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Newspaper> newsArrayList = QueryUtils.fetchNewsData(url);
        return newsArrayList;
    }
}
