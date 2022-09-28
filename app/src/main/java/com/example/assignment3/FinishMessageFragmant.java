package com.example.assignment3;
/*
When all the binoculars have been found in the game it shows the end congratulations message
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class FinishMessageFragmant extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Create the view
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.finish_message, null);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                getActivity().finish();
            }
        };
        return new AlertDialog.Builder(getActivity())
                .setTitle("CONGRATULATIONS YOU FOUND ALL THE BINOCULARS")
                .setView(view)
                .setPositiveButton("ok", listener)
                //.setNegativeButton("no",listener)
                .create();
    }
}
