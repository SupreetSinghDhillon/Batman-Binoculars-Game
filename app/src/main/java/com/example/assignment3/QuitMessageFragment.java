package com.example.assignment3;
/*
Displays the message when you want to quit a game
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class QuitMessageFragment extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Create the view
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.message_layout,null);

        //A button listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch(which)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        getActivity().finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //dont do anything
                        break;
                }
            }
        };

        //Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle("Are you sure you want to exit the game")
                .setView(view)
                .setPositiveButton("yes",listener)
                .setNegativeButton("no",listener)
                .create();
    }
}
