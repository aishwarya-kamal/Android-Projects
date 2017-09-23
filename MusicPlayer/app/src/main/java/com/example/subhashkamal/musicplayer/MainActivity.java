package com.example.subhashkamal.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // global bcz onClickListeners have to access it
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the media player when the activity is first created so that the mediaplayer object will be ready whenever we hit play
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.million_pound_girl);

        Button playButton = (Button) findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer){
                        Toast.makeText(MainActivity.this, "I'm done!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button pauseButton = (Button) findViewById(R.id.pause_button);

        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                mediaPlayer.pause();
            }
        });
    }
}
