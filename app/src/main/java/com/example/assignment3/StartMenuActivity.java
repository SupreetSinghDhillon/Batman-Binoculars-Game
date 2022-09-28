package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
/*
This activity displays the starting menu of the game
 */

public class StartMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setUpMainActivityButton();

        startAnimations();
    }

    private void startAnimations() {
        Animation animation = AnimationUtils.loadAnimation(StartMenuActivity.this,R.anim.fadeout);
        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setAnimation(animation);
    }

    private void setUpMainActivityButton() {
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.makeIntent(StartMenuActivity.this);
                startActivity(intent);
            }
        });
    }
}