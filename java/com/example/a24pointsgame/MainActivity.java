package com.example.a24pointsgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton playBnt;
    private ImageButton guideBnt;
    private ImageButton aboutBnt;
    private ImageButton play1Bnt;

    private ImageButton play2Bnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBnt = findViewById(R.id.play);
        guideBnt = findViewById(R.id.guide);
        aboutBnt = findViewById(R.id.about);
        play1Bnt = findViewById(R.id.play1);
        play2Bnt = findViewById(R.id.play2);


        playBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
        play1Bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity2.class));
            }
        });
        play2Bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity3.class));
            }
        });
        guideBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Guide.class));
            }
        });

        aboutBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutMe.class));
            }
        });

    }

}