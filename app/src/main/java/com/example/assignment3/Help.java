package com.example.assignment3;
/*
Displays the help activity which has the rules of the game, references and CMPt 276 website hyperlink
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    public static Intent makeIntent(MainActivity mainActivity) {
        Intent intent = new Intent(mainActivity,Help.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("HELP");
        setContentView(R.layout.activity_help);
        setUpHyperLink();
    }

    private void setUpHyperLink() {
        TextView tv = findViewById(R.id.author);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}