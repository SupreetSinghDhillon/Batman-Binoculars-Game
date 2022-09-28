
package com.example.assignment3;

/*
        Executes a full game of Batman Binocular Finder and print the congratulations message.
 */

import static com.example.assignment3.R.drawable.binoculars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {


    private int sizeOfBoard = 0;
    private int numberOfObjects = -1;

    private int countTheObjectsPlaced = 0;
    private int numRows=2;
    private int numColumns=2;
    private int numberOfObjectsFound = 0;
    private int numberOfScans = 0;

    Button buttons[][] = new Button[numRows][numColumns];

    List<Point> buttonList = new ArrayList();
    List<Point> scanCheck = new ArrayList();
    List<Point> checkAlreadyScanned = new ArrayList<>();

    public static Intent makeIntent(MainActivity mainActivity, int boardSize, int numberOfObjects) {
        Intent intent = new Intent(mainActivity, Game.class);
        intent.putExtra("Board Size",boardSize);
        intent.putExtra("Number of objects",numberOfObjects);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.setTitle("GAME");

        Intent intent = getIntent();
        sizeOfBoard = intent.getIntExtra("Board Size",0);
        numberOfObjects = intent.getIntExtra("Number of objects",-1);
        numberOfObjectsFound = numberOfObjects;


        setUpDimensions();

        setUpBoard();

        placeObjects();

        TextView textView = findViewById(R.id.numberOfObjects);
        textView.setText("Number of Binoculars left = " + numberOfObjectsFound);

        TextView tv = findViewById(R.id.numberOfScans);
        tv.setText("Number of scans used = " + numberOfScans);
    }

    private void placeObjects() {
        if(numRows==4&&numColumns==6&& numberOfObjects ==20)
        {
            for(int i = 0;i<numRows;i++)
            {
                for(int j = 0;j<numColumns-1;j++)
                {
                    Point point = new Point(i,j);
                    buttonList.add(point);
                }
            }
        }
        else {
            while (countTheObjectsPlaced < numberOfObjects) {
                int random = new Random().nextInt(((numColumns - 1) - 1) + 1) + 1;
                int random2 = new Random().nextInt(((numRows - 1) - 1) + 1) + 1;
                Point point = new Point(random2, random);
                if (!buttonList.contains(point)) {
                    buttonList.add(point);
                    countTheObjectsPlaced++;
                }
            }
            scanCheck = buttonList;
        }
    }

    private void setUpBoard() {
        TableLayout table =(TableLayout)findViewById(R.id.table);
        for(int row = 0; row<numRows;row++)
        {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(

                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    0.0f));

            table.addView(tableRow);
            for(int column =0; column<numColumns;column++)
            {
                final int finalR = row;
                final int finalC = column;
                Button btn = new Button(this);
                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(finalR, finalC);
                    }
                });
                tableRow.addView(btn);
                    buttons[row][column] = btn;
            }
        }
    }

    private void gridButtonClicked(int final_row, int final_col) {

        lockButtonSizes();

        Point point = new Point(final_row,final_col);

        if(buttonList.contains(point)) {

            Button button = buttons[final_row][final_col];
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), binoculars);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resources = getResources();
            button.setBackground(new BitmapDrawable(resources, scaledBitmap));

            MediaPlayer mediaPlayer = MediaPlayer.create(Game.this,R.raw.binocular);
            mediaPlayer.start();


            if(scanCheck.contains(point))
            {
                numberOfObjectsFound--;
                TextView textView = findViewById(R.id.numberOfObjects);
                textView.setText("Number of Binoculars left = " + numberOfObjectsFound);
                scanCheck.remove(point);
            }
            if(numberOfObjectsFound==0)
            {
                FragmentManager manager = getSupportFragmentManager();
                FinishMessageFragmant dialog = new FinishMessageFragmant();
                dialog.show(manager,"MessageDialog");
            }

        }
        else
        {
            if(!checkAlreadyScanned.contains(point)) {
                checkAlreadyScanned.add(point);
                numberOfScans++;
            }
            int columnNumber = 0;

            int rowNumber = 0;

            //Scanning upwards
            if(final_row!=0)
            {
                int checkRow = final_row-1;
                while(checkRow>-1)
                {
                    Point toCheck = new Point(checkRow,final_col);
                    if(buttonList.contains(toCheck))
                        columnNumber++;
                    checkRow--;
                }
            }
            //Scanning downwards
            if(final_row!=numRows)
            {
                int checkRow = final_row+1;
                while(checkRow<numRows)
                {
                    Point toCheck = new Point(checkRow,final_col);
                    if(buttonList.contains(toCheck))
                        columnNumber++;
                    checkRow++;
                }
            }
            //Scanning left
            if(final_col!=0)
            {
                int checkCol = final_col-1;
                while(checkCol>-1)
                {
                    Point toCheck = new Point(final_row,checkCol);
                    if(buttonList.contains(toCheck))
                        rowNumber++;
                    checkCol--;
                }
            }
            //Scanning right
            if(final_row!=numRows)
            {
                int checkCol = final_col+1;
                while(checkCol<numColumns)
                {
                    Point toCheck = new Point(final_row,checkCol);
                    if(buttonList.contains(toCheck))
                        rowNumber++;
                    checkCol++;
                }
            }

            Button button = buttons[final_row][final_col];

            MediaPlayer mediaPlayer = MediaPlayer.create(Game.this,R.raw.buttonpress);
            mediaPlayer.start();

            button.setText(""+(rowNumber+columnNumber));

            //numberOfScans++;
            TextView tv = findViewById(R.id.numberOfScans);
            tv.setText("Number of scans used = " + numberOfScans);
        }
    }

    private void lockButtonSizes() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                Button button = buttons[row][col];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);
                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }
    private void setUpDimensions() {

        if(sizeOfBoard == 4)
        {
            numRows = 4;
            numColumns = 6;
        }
        if(sizeOfBoard == 5)
        {
            numRows = 5;
            numColumns = 10;
        }
        if(sizeOfBoard == 6)
        {
            numRows = 6;
            numColumns = 15;
        }
        Button duplicateButtons[][] = new Button[numRows][numColumns];
        buttons = duplicateButtons;
    }
}