package com.example.cashbook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class HelpFragment extends Fragment {


    View view;
    ListView helpList;
    ArrayList<String> descriptions = new ArrayList<String>();

    public HelpFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_help, container, false);
        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        descriptions.add("List 1");
        descriptions.add("List 2");
        helpList = view.findViewById(R.id.helpList);
        //ArrayAdapter ad = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,descriptions);
        //helpList.setAdapter(ad);
    }
}