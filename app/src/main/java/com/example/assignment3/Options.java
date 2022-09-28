package com.example.assignment3;
/*
This displays the options activity to change game characteristics - size of board and number of binoculars
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    public static Intent makeIntent(MainActivity mainActivity) {
        Intent intent = new Intent(mainActivity, Options.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        this.setTitle("OPTIONS");
        createNumberOfObjectsRadioButtons();
        createBoardSizeRadioButtons();

        int savedValue = getSizeOfBoard(this);
        Toast.makeText(this,"saved value is " + savedValue,Toast.LENGTH_SHORT).show();

        Animation animation = AnimationUtils.loadAnimation(Options.this,R.anim.fadeout);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setAnimation(animation);
    }

    private void createBoardSizeRadioButtons() {
        RadioGroup group = findViewById(R.id.radiogroupboardsize);
        int[] num = getResources().getIntArray(R.array.Size_of_Board);
        for (int i = 0; i < num.length; i++) {
            int size = num[i];
            RadioButton button = new RadioButton(this);
            int column = 6;
            if(i==1) {
                column = 10;
            }
            if(i==2)
            {
                column = 15;
            }
            button.setText(size + " X " + column);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Toast.makeText(Options.this, "" + size, Toast.LENGTH_SHORT).show();
                    saveSizeOfBoard(size);
                }
            });

            group.addView(button);

            if(size == getSizeOfBoard(this))
                button.setChecked(true);
        }

    }

    private void createNumberOfObjectsRadioButtons() {

        RadioGroup group = findViewById(R.id.radiogroup);
        int[] num = getResources().getIntArray(R.array.Number_of_objects);
        for (int i = 0; i < num.length; i++) {
            int number = num[i];
            RadioButton button = new RadioButton(this);
            button.setText(number + " Binoculars");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(Options.this, " You gave Batman and the guys " + number + " Helpers", Toast.LENGTH_SHORT).show();
                    saveNumberOfObjects(number);
                }
            });

            group.addView(button);

            if(number == getNumObjects(this)){
                button.setChecked(true);
            }
        }

    }

    private void saveNumberOfObjects(int number) {

        SharedPreferences Prefs = this.getSharedPreferences("AppPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = Prefs.edit();
        editor.putInt("Number of objects",number);
        editor.apply();

    }

    static public int getNumObjects(Context context)
    {
        SharedPreferences Prefs = context.getSharedPreferences("AppPref",MODE_PRIVATE);
        return Prefs.getInt("Number of objects",5);
    }

    private void saveSizeOfBoard(int num)
    {
        SharedPreferences prefs = this.getSharedPreferences("appPref",MODE_PRIVATE);
        SharedPreferences.Editor editorTwo = prefs.edit();
        editorTwo.putInt("Size of board",num);
        editorTwo.apply();
    }
    static public int getSizeOfBoard(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("appPref",MODE_PRIVATE);
        return prefs.getInt("Size of board",4);
    }
}