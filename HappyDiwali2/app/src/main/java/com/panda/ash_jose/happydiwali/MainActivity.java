package com.panda.ash_jose.happydiwali;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterMessage(View v) {
        Intent goToMessage = new Intent();
        goToMessage.setClass(this, message.class);
        startActivity(goToMessage);

    }

    public void enterAbout(View v) {
        Intent goToMessage = new Intent();
        goToMessage.setClass(this, about.class);
        startActivity(goToMessage);

    }
    public void enterSurprise(View v) {
        Intent goToMessage = new Intent();
        goToMessage.setClass(this, surprise.class);
        startActivity(goToMessage);

    }
    public void enterHistory(View v) {
        Intent goToMessage = new Intent();
        goToMessage.setClass(this, history.class);
        startActivity(goToMessage);

    }
}
