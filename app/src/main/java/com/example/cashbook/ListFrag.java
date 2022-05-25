package com.example.cashbook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cashbook.insidenotebook.ApplicationClass;


public class ListFrag extends Fragment {


    View view;
    RecyclerView list;
    RecyclerView.Adapter myadapter;
    RecyclerView.LayoutManager layoutManager;

    public ListFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);


            list = view.findViewById(R.id.list);
            list.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this.getActivity());
            list.setLayoutManager(layoutManager);


            myadapter = new cashBookAdapter(this.getActivity(), ApplicationClass.book);
            list.setAdapter(myadapter);




    }

    public void notifyChange()
    {
        myadapter.notifyDataSetChanged();
    }
}