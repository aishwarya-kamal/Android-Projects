package com.example.subhashkamal.learntelugu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding the family view
        LinearLayout family = (LinearLayout) findViewById(R.id.familyView);

        // Attach the click listener to that view
        family.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        // Finding the color view
        LinearLayout colors = (LinearLayout) findViewById(R.id.colorsView);

        // Attach the click listener to that view
        colors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        // Finding the fruits view
        LinearLayout fruits = (LinearLayout) findViewById(R.id.fruitsView);

        // Attach the click listener to that view
        fruits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent fruitsIntent = new Intent(MainActivity.this, FruitsActivity.class);
                startActivity(fruitsIntent);
            }
        });

        // Finding the body parts view
        LinearLayout body = (LinearLayout) findViewById(R.id.bodyView);

        // Attach the click listener to that view
        body.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent bodyIntent = new Intent(MainActivity.this, BodyActivity.class);
                startActivity(bodyIntent);
            }
        });

        // Finding the animals view
        LinearLayout animals = (LinearLayout) findViewById(R.id.animalsView);

        // Attach the click listener to that view
        animals.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent animalsIntent = new Intent(MainActivity.this, AnimalsActivity.class);
                startActivity(animalsIntent);
            }
        });

        // Finding the phrases view
        LinearLayout phrases = (LinearLayout) findViewById(R.id.phrasesView);

        // Attach the click listener to that view
        phrases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });

        // Finding the phrases view
        LinearLayout quiz = (LinearLayout) findViewById(R.id.quizView);

        // Attach the click listener to that view
        quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent quizIntent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(quizIntent);
            }
        });
    }

    public void openNumbersList(View view) {
        Intent i = new Intent(this, NumbersActivity.class);
       startActivity(i);
    }

}