package com.sdm.mgp2023;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class PauseConfirmDialogFragment extends DialogFragment {

    public static boolean IsShown = false;

    // To create the popup dialog interface.
    // It will have 2 buttons.

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        IsShown = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Pause")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                    //Trigger Pause
                GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                IsShown = false;
            }

        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // We will define what to do when -ve button is pressed
                        // Do not want to pause.
                        GameSystem.Instance.SetIsPaused(GameSystem.Instance.GetIsPaused());
                        IsShown = false;
                    }
        });
        //Create our customised Dialog popup
        return builder.create();
    }

    // Part of the dialog pop up interface to indicate to cancel or not to use it.
    @Override
    public void onCancel (DialogInterface dialog){
        IsShown = false;
    }

    @Override
    public void onDismiss (DialogInterface dialog){
        IsShown = false;
    }

}
