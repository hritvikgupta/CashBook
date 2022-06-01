package com.example.cashbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cashbook.insidenotebook.ApplicationClass;
import com.example.cashbook.insidenotebook.MaintainFinalBalance;
import com.example.cashbook.insidenotebook.NoteBookDetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements cashBookAdapter.ItemClicked, DialogFragment.dialogClicked, DatePickerDialog.OnDateSetListener, BottomFragment.options{

    Button btnAddBook;
    FragmentManager fragmentManager;
    ListFrag lfrag;
    DialogFragment dfrag;
    BottomFragment bfrag;
    String name;
    RecyclerView.Adapter hAdapter;
    String tag;
    FragmentTransaction ft;
    Button datebutton;
    int hide_pos = 0;
    TextView setDate;
    ArrayList<Books>book;
    Dictionary dict = new Hashtable();
    int booksize;
    SearchView searchView;
    ArrayList<String> namesBook = new ArrayList<String>();
    LinearLayout l1,l2,l3;
    ImageView imageView2, imageView3, imageView4;
    ListFrag list_frag;
    Setting settingActivity;
    String date;
    boolean dataClicked = false;
    int flag = 0;
    int clickedIndex;
    boolean mainLongClicked = false;
    int mainLongClickedPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences sharedPreferences = getSharedPreferences("SP",MODE_PRIVATE);
        Boolean darkon = sharedPreferences.getBoolean("darkon",false);
        if(darkon == true)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        list_frag = (ListFrag) getSupportFragmentManager().findFragmentById(R.id.list);

        searchView = findViewById(R.id.serchView);
        fragmentManager = this.getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        lfrag = (ListFrag) fragmentManager.findFragmentById(R.id.list);
        dfrag = (DialogFragment) fragmentManager.findFragmentById(R.id.Dialog);
        bfrag = (BottomFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView4);

        book = new ArrayList<Books>();

        if(ApplicationClass.book.isEmpty())
        {
            startNoteBook();

        }
        //hideNoteBook();

        btnAddBook = findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //showNoticeDialog();
                //Toast.makeText(MainActivity.this, "Successful",Toast.LENGTH_SHORT).show();
                //hideNoteBook();
                showNoticeDialog();
                //hideNoteBook();



                //callDate(datebutton);
                //createBook(name);



            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //Toast.makeText(MainActivity.this, "Ok",Toast.LENGTH_SHORT).show();

                if(namesBook.contains(s))
                {
                    Toast.makeText(MainActivity.this, "Found Element" + namesBook,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No Results Matched" +namesBook,Toast.LENGTH_SHORT).show();

                }
                return false;

            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        date = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).format(new Date());


    }

    @Override
    public void onItemClicked(int index) {


        clickedIndex = index;
        if(ApplicationClass.book.get(index).getName().equals("Add Expense Book"))
        {
            //hideNoteBook();
            //Toast.makeText(MainActivity.this, "Entereed" + flag, Toast.LENGTH_SHORT).show();
            showNoticeDialog();
            hideNoteBook();
        }
        else {

            booksize = ApplicationClass.book.size();

            ApplicationClass.setBooksize(booksize);

            for (int i = 0; i < booksize; i++) {
                ApplicationClass.mBook.add(new MaintainFinalBalance(0, 0, 0, 0));
            }

            //Toast.makeText(MainActivity.this, ApplicationClass.mBook.get(index).getNetBalance(),Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, NoteBookDetails.class);
            intent.putExtra("index", String.valueOf(index));
            //Toast.makeText(MainActivity.this, "Click"+ ApplicationClass.book.get(index).getName(), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

    }

    @Override
    public void onLongMainClicked(int index) {
        mainLongClicked = true;
        mainLongClickedPosition = index;
        showNoticeDialog();
    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, EditText etText) {
        //One Way to get the dialog values is to use this Dialog.getDialog where dialog is an instance
        //of dialog fragment we have created and passed to from there only.

        //Second method is to pass the text which was written there and use it here by passing as
        //Here

        //But this one is the best way to do so.
        if(mainLongClicked == false) {
            Dialog dialogView = dialog.getDialog();
            EditText et = (EditText) dialogView.findViewById(R.id.etText);
            name = (String) et.getText().toString();
            namesBook.add(name);
            //Add Create NoteBook Here oftherwise it will be created after clicking Add Notebook twice
            // also add notifyDatachange in createBook after data being added for the same reason
            if (name.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please Provide Name", Toast.LENGTH_SHORT).show();
                showNoticeDialog();


            } else {
                createBook(name, setDate.getText().toString());

            }
        }
        else if(mainLongClicked == true)
        {
            mainLongClicked = false;
            Dialog dialogView = dialog.getDialog();
            EditText et = (EditText) dialogView.findViewById(R.id.etText);
            name = (String) et.getText().toString();
            namesBook.add(name);
            if(!name.isEmpty())
            {
                ApplicationClass.book.get(mainLongClickedPosition).setName(name);
                lfrag.notifyChange();
            }
            //Add Create NoteBook Here oftherwise it will be created after clicking Add Notebook twice
            // also add notifyDatachange in createBook after data being added for the same reason

        }
        //Here we have used the setDate parameter to set the date of what we taken input from the date


    }


    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    //This is my Function not inbuild function to pass the date
    //created in dialog fragment and took the dialog view items and pass them in Here
    // this on button can be created right above the positive click button in dialogfragment
    //But the view can not be set on selected date as "onDataSet" This one and not belowone can
    //not be used on dialog fragement in onCreate Dialog. Therefore it is better to pass the
    //Button via function and use it here


    //My Function
    @Override
    public void onDateset(Button btn, TextView setDate) {
        datebutton = btn;
        this.setDate =setDate;
        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                dataClicked = true;
                com.example.cashbook.DatePicker mdate = new com.example.cashbook.DatePicker();
                mdate.show(getSupportFragmentManager(),"Select Date");



            }
        });


    }


    public void createBook(String name, String tag)
    {
        if(!dataClicked)
        {
            tag = date;
        }
        else
        {
            dataClicked = false;
        }

        Books b1 = new Books(name, tag);
        ApplicationClass.book.add(b1);
        lfrag.notifyChange();

    }



    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
        Calendar mcalender = Calendar.getInstance();
        mcalender.set(Calendar.YEAR, i);
        mcalender.set(Calendar.MONTH, i1);
        mcalender.set(Calendar.DAY_OF_MONTH, i2);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mcalender.getTime());
        setDate.setText(selectedDate);

    }

    @Override
    public void OnOptionClicked(ImageView iv1,ImageView iv2, ImageView iv3) {
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddBook.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.VISIBLE);
                hide_pos = 1;
                getSupportFragmentManager().beginTransaction()
                        .show(list_frag).commit();
                setTitle("Cash Book");

            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   hide_pos=2;
                   //getSupportFragmentManager().beginTransaction().hide(list_frag).commit();
                   Intent intent = new Intent(MainActivity.this, com.example.cashbook.HelpActivity.class);
                   startActivity(intent);
                   setTitle("Cash Book");
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.cashbook.Setting.class);
                startActivity(intent);


            }
        });
    }


    public void onHelpClicked(ListView helpList) {
        /*
        getSupportFragmentManager().beginTransaction()
                .show(helpFragment).hide(list_frag).commit();
        ArrayList<String> descriptions = new ArrayList<String>();
        descriptions.add("List 1");
        descriptions.add("List 2");
        ArrayAdapter ad = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,descriptions);
        helpList.setAdapter(ad);

         */
    }

    public void startNoteBook()
    {
        if(!dataClicked)
        {
            Books bstart = new Books("Add Expense Book", date);
            ApplicationClass.book.add(bstart);
        }
        else{
            dataClicked = false;
            Books bstart = new Books("Add Expense Book", "Select Date");
            ApplicationClass.book.add(bstart);
        }


    }
    public void hideNoteBook()
    {
        ApplicationClass.book.remove(ApplicationClass.book.remove(0));

    }

}