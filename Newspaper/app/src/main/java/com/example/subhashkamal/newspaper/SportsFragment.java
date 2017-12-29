package com.example.subhashkamal.newspaper;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SportsFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<Newspaper>>{
    /*
        *  This is NINO CODE PLEASE DONT DELETE IT ...
        */
    private static final String NEWS_URL =
            "http://newsapi.org/v1/articles?source=bbc-sport&sortBy=top&apiKey=d1d394d29cef4cf1b0481dbdc0e947ee";

    /**
     * Constant value for the news loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int LOADER_ID = 3;

    /** Adapter for the list of earthquakes */
    private NewspaperAdapter mAdapter;

    private Loader<List<Newspaper>> loader;


    public SportsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loader = getLoaderManager().initLoader(LOADER_ID, getArguments(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Setting the title of the actionbar
        getActivity().setTitle("Sports");

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) rootView.findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new NewspaperAdapter(getActivity(), new ArrayList<Newspaper>());

        newsListView.setAdapter(mAdapter);


        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news.
        /*  newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Newspaper currentNews = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getmUrl());

                // Create a new intent to view the news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        }); */

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            android.support.v4.app.LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LOADER_ID, null, SportsFragment.this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            //View loadingIndicator = findViewById(R.id.loading_indicator);
            //loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            //mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        return rootView;
    }

    @Override
    public Loader<List<Newspaper>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        return new NewspaperLoader(getContext(), NEWS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Newspaper>> loader, List<Newspaper> newspapers) {
        mAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newspapers != null && !newspapers.isEmpty()) {
            mAdapter.addAll(newspapers);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Newspaper>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

}
