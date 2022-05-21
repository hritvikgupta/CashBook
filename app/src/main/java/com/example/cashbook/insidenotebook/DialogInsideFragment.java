package com.example.cashbook.insidenotebook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cashbook.R;


public class DialogInsideFragment extends DialogFragment {

    Button dateButton2;
    EditText amountInOut, insideTag;
    dialogInsideClicked activity;

    public DialogInsideFragment() {
        // Required empty public constructor
    }
    public interface dialogInsideClicked
    {
        public void onSaveClicked(DialogInsideFragment dialog);
        public void onCancelClicked(DialogInsideFragment dialog);
        public void dateSetting(Button dateButton2);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_inside, container, false);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (dialogInsideClicked) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View inflatedView = layoutInflater.inflate(R.layout.fragment_dialog_inside,null);

        dateButton2 = inflatedView.findViewById(R.id.dataButton2);
        amountInOut = inflatedView.findViewById(R.id.amountInOut);
        insideTag = inflatedView.findViewById(R.id.insideTag);

        b.setView(inflatedView);
        activity.dateSetting(dateButton2);


        b.setCancelable(false)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.onSaveClicked(DialogInsideFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            activity.onCancelClicked(DialogInsideFragment.this);
                    }
                });


        return b.create();





    }

}