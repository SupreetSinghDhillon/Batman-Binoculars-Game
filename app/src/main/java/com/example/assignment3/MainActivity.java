package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
This is the main menu of the game from this you can start a game, change options, exit the game and see the help screen.
 */

public class MainActivity extends AppCompatActivity {

    private int numberOfBinoculars = 1;
    private int boardSize = -1;

    public static Intent makeIntent(StartMenuActivity startMenuActivity) {
        Intent intent = new Intent(startMenuActivity,MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.setTitle("Batman Binocular Finder");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpExitGame();
        setUpGameBtn();
        setUpOptionsBtn();

        refreshScreen();

        onResume();

        setUpHelpButton();

    }

    private void setUpHelpButton() {
        Button btn = findViewById(R.id.helpbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Help.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void refreshScreen() {
        int numberObjects = Options.getNumObjects(this);
        numberOfBinoculars = numberObjects;
        int sizeofBoard = Options.getSizeOfBoard(this);
        boardSize = sizeofBoard;
    }

    private void setUpOptionsBtn() {
        Button btn = findViewById(R.id.optionsbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Options.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void setUpGameBtn() {

        Button btn = findViewById(R.id.startbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Game.makeIntent(MainActivity.this, boardSize,numberOfBinoculars);
                startActivity(intent);
            }
        });

    }

    private void setUpExitGame() {
        Button btn = findViewById(R.id.exitGameBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                QuitMessageFragment dialog = new QuitMessageFragment();
                dialog.show(manager,"MessageDialog");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshScreen();
    }

    }
