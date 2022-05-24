package com.example.cashbook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class BottomFragment extends Fragment {

    LinearLayout l1;
    ImageView imBook, imHelp, imSettings;
    View view;
    options activity;

    public interface options
    {
        void OnOptionClicked(ImageView iv1, ImageView iv2, ImageView iv3);
    }
    public BottomFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (options) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bottom, container, false);
        return view;

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        l1 = view.findViewById(R.id.l1);
        imBook = view.findViewById(R.id.imageView2);
        imHelp = view.findViewById(R.id.imageView3);
        imSettings = view.findViewById(R.id.imageView4);
        activity.OnOptionClicked(imBook, imHelp, imSettings);

    }
}