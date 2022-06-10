package com.example.cashbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class BottomFragment extends Fragment {

    LinearLayout l1;
    ImageView imBook, imHelp, imSettings;
    View view;
    options activity;
    private static TextView bookLang, helpLang, settingLang;
    Context context;
    Resources resources;
    String lang;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;



    public interface options
    {
        void OnOptionClicked(ImageView iv1, ImageView iv2, ImageView iv3);
;    }

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
        helpLang = view.findViewById(R.id.helpLang);
        bookLang = view.findViewById(R.id.bookLang);
        settingLang = view.findViewById(R.id.settingLang);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SP",getContext().MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        Boolean b = sharedPreferences.getBoolean("langHind", false);
        Boolean dark = sharedPreferences.getBoolean("darkon",false);
        if(b)
        {
            myEdit.putBoolean("langHind", true);
            setText("hi");
        }
        else
        {
            myEdit.putBoolean("landHind", false);
            setText("en");
        }

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

    public void setText(String lang)
    {
        context = LocaleHelper.setLocale(getActivity(),lang );
        resources = context.getResources();
        bookLang.setText(resources.getString(R.string.books));
        helpLang.setText(resources.getString(R.string.help));
        settingLang.setText(resources.getString(R.string.settings));
    }



    public void setLanguageBottomFragment(String b, String h, String s)
    {
        bookLang.setText(b);
        helpLang.setText(h);
        settingLang.setText(s);


    }
}