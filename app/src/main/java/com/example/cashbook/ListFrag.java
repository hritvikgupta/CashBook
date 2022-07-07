package com.example.cashbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.cashbook.insidenotebook.ApplicationClass;

import java.util.ArrayList;


public class ListFrag extends Fragment {


    View view;
    RecyclerView list;
    int removingitem;
    Boolean del = false;
    RecyclerView.Adapter myadapter, filterAdapter;
    private cashBookAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    searchList activity;
    String bookNames;
    SearchView searchView;
    ArrayList<Books> filteredBooks;
    Boolean search = false;

    public ListFrag() {
        // Required empty public constructor
    }
    public interface searchList
    {
        public void onSearchList(RecyclerView.Adapter adapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (searchList) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        searchView = view.findViewById(R.id.searchView);
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

            activity.onSearchList(myadapter);
            searchView.setVisibility(View.GONE);

/*

            searchView.setOnTouchListener(new View.OnTouchListener() {
                 @Override
                 public boolean onTouch(View view, MotionEvent motionEvent) {
                     ((MainActivity)getActivity()).getSupportActionBar().hide();

                     return false;
                 }
             });
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    search = true;
                    filter(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    ((MainActivity)getActivity()).getSupportActionBar().hide();

                    return false;
                }
            });

            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    startActivity(new Intent(getActivity(),MainActivity.class));
                    return false;
                }
            });


 */



    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //((MainActivity)getActivity()).getSupportActionBar().hide();

    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        // running a for loop to compare elements.

        //for(int i=0 ; i<ApplicationClass.book.size();i++)
       // {
        ArrayList<Books> filteredBooks =  new ArrayList<>();

        //}

        for (Books item : ApplicationClass.book) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {

                search = true;
                //Toast.makeText(getActivity(),""+item.getName(),Toast.LENGTH_SHORT).show();
                filteredBooks.add(item);
                //Toast.makeText(getActivity(),"Hola",Toast.LENGTH_SHORT).show();
            }
            filterAdapter = new cashBookAdapter(this.getActivity(), filteredBooks);
            list.setAdapter(filterAdapter);

        }


    }

    public void notifyChange()
    {
        myadapter.notifyDataSetChanged();
    }

    public void removeItem(int actualPosition) {
       // Toast.makeText(getActivity(),"entereed",Toast.LENGTH_SHORT).show();
        myadapter.notifyItemRemoved(actualPosition);
        myadapter.notifyItemRangeChanged(actualPosition,1);
    }


}