package com.example.subhashkamal.block07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    NumberPicker possibilities;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        possibilities = (NumberPicker) findViewById(R.id.numberPicker);
        webView = (WebView) findViewById(R.id.webView);
        String[] possibilitiesStrings = {
                "Android",
                "Check list",
                "Panda",
                "Dragon"
        };

        possibilities.setDisplayedValues(possibilitiesStrings);
        possibilities.setMinValue(0);
        possibilities.setMaxValue(possibilitiesStrings.length - 1);
    }

    public void navigate(View v) {
        int choice = possibilities.getValue();
        if (choice == 0) {
            webView.setWebViewClient(new WebViewClient()); // to display the links in the webpage in the APP and not in the phone browser(not create new activity)
            webView.loadUrl("file:///android_asset/android.html");
        }else if (choice == 1)
            webView.loadUrl("file:///android_asset/checklist.html");
        else if (choice == 2){
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("https://en.wikipedia.org/wiki/Giant_panda");
        }else if (choice == 3)
            webView.loadUrl("https://en.wikipedia.org/wiki/Dragon");
    }


}
