package com.example.cashbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.cashbook.insidenotebook.ApplicationClass;
import com.example.cashbook.insidenotebook.MaintainFinalBalance;
import com.example.cashbook.insidenotebook.NoteBookDetails;
import com.example.cashbook.insidenotebook.expenseBook;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements cashBookAdapter.ItemClicked, DialogFragment.dialogClicked, DatePickerDialog.OnDateSetListener, BottomFragment.options, ListFrag.searchList{

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
    FloatingActionButton fab;
    Boolean btnVisible = true;
    ExtendedFloatingActionButton efab;
    int canceled = 0;
    Button Delete;
    Boolean val = false;
    com.example.cashbook.BottomFragment bm;
    Boolean langHind;
    Context context;
    Resources resources;
    String lang;
    String firstBook;
    RecyclerView.Adapter myadapter;
    cashBookAdapter adapter_cash;
    ActionBar actionBar;
    Boolean darkon;
    Toolbar toolbar;
    SharedPreferences.Editor clickColor, mainBalColor, logEdit;
    Boolean condition, newBook;
    SharedPreferences sp, sharedPreferencesbooks;
    private UiModeManager uiModeManager;
    cashBookAdapter.ViewHolder holder;
    NoteBookDetails notebook;
    int amount =0;
    String number;
    int replace = 0;


    SharedPreferences sharedPreferencesBooks;
    SharedPreferences.Editor booksEdit;
    Boolean stored, res_b, storedn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         actionBar = getSupportActionBar();
         bm = new com.example.cashbook.BottomFragment();
         sharedPreferencesBooks = getSharedPreferences("booksLog", MODE_PRIVATE);
         booksEdit = sharedPreferencesBooks.edit();
         Boolean restart = getIntent().getBooleanExtra("restart", false);
         res_b = restart;
         //sharedPreferencesbooks = getSharedPreferences("booksLog", MODE_PRIVATE);
         //storedn1 = sharedPreferencesbooks.getBoolean("storedin1", false);
         //number = getIntent().getStringExtra("number");
         //ApplicationClass.userIdentity.get(0).setUserNumber(number);
         //Toast.makeText(MainActivity.this, ApplicationClass.userIdentity.get(0).getUserNumber(),Toast.LENGTH_SHORT).show();

        //actionBar.setTitle(HtmlCompat.fromHtml("<font color='#0000000'>"+resources.getString(R.string.ExpenseBook)+"</font>", HtmlCompat.FROM_HTML_MODE_LEGACY));

        SharedPreferences sharedPreferences = getSharedPreferences("SP",MODE_PRIVATE);
        clickColor = sharedPreferences.edit();
        darkon = sharedPreferences.getBoolean("darkon",false);
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES &&!darkon)
        {
            //Toast.makeText(MainActivity.this, "hola",Toast.LENGTH_SHORT).show();

            //clickColor.putBoolean("darkon", true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
        else if(nightModeFlags != Configuration.UI_MODE_NIGHT_YES && darkon)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }


        langHind = sharedPreferences.getBoolean("langHind", false);
        if(langHind)
            lang = "hi";
        else
            lang = "en";

        context = LocaleHelper.setLocale(MainActivity.this,lang );
        resources = context.getResources();
        setTitle(resources.getString(R.string.ExpenseBook));
        if(darkon == true &&nightModeFlags == Configuration.UI_MODE_NIGHT_YES )
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        list_frag = (ListFrag) getSupportFragmentManager().findFragmentById(R.id.list);

        //searchView = findViewById(R.id.serchView);
        fragmentManager = this.getSupportFragmentManager();
        efab = findViewById(R.id.add_fab);
        ft = fragmentManager.beginTransaction();
        lfrag = (ListFrag) fragmentManager.findFragmentById(R.id.list);
        dfrag = (DialogFragment) fragmentManager.findFragmentById(R.id.Dialog);
        bfrag = (BottomFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView4);

        book = new ArrayList<Books>();
        firstBook = resources.getString(R.string.AddExpenseBook);

        stored = sharedPreferencesBooks.getBoolean("Storedn6", false);
        //q343Toast.makeText(MainActivity.this, "s" + ApplicationClass.book.get(0).getName(), Toast.LENGTH_SHORT).show();
            if (ApplicationClass.book.isEmpty()) {
                //Toast.makeText(MainActivity.this, "s"+ stored, Toast.LENGTH_SHORT).show();
                startNoteBook();
                //hideNoteBook();

            }
            else if(ApplicationClass.book.get(0).getName().equals("Add Expense Book"))
            {
                hideNoteBook();
            }
            else if (ApplicationClass.book.get(0).getName().equals("Add Expense Book") || ApplicationClass.book.get(0).getName().equals("व्यय पुस्तक जोड़ें")) {
                hideNoteBook();
                startNoteBook();


        }

        //condition = ApplicationClass.book.get(0).getName().equals("Add Expense Book") || ApplicationClass.book.get(0).getName().equals("व्यय पुस्तक जोड़ें");

        //hideNoteBook();

/*
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


 */


        efab.setBackgroundColor(Color.parseColor("#243b55"));
        efab.setText(resources.getString(R.string.AddExpenseBook));
        efab.shrink();
        efab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Add a Expense Book",Snackbar.LENGTH_LONG).setAction("Action", null).show();
                if(!efab.isExtended()) {
                    efab.extend();
                    newBook = true;
                    showNoticeDialog();


                }
                else
                {
                    efab.shrink();
                }


            }
        });
        if(darkon ==  true)
        {
            efab.setBackgroundColor(Color.WHITE);
            efab.setIconTint(ColorStateList.valueOf(Color.BLACK));
        }



/*
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

 */

        date = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).format(new Date());
        //setTitle(resources.getString(R.string.ExpenseBook));
        setActionBarColors();
        int size = sharedPreferencesBooks.getInt("Size", 1);
       // hideNoteBook();
        //Toast.makeText(MainActivity.this, "s"+size+sharedPreferencesBooks.getInt("amount"+2, 1), Toast.LENGTH_SHORT).show();
        if(stored && restart)
        {
            hideNoteBook();
            int am =0;
            for(int i =0; i<size;i++){
                     String name = sharedPreferencesBooks.getString("Book"+i,"null");
                     String date_stored = sharedPreferencesBooks.getString("date"+i, "Select Date");
                //int amount_stored = sharedPreferencesBooks.getInt("amount"+i, 0);
                     sharedPreferencesbooks = getSharedPreferences("booksLog"+i, MODE_PRIVATE);
                     int  amount = sharedPreferencesbooks.getInt("currentNet"+i,0);
                     if(i==0)
                     {
                         am = amount;
                     }
                     ApplicationClass.book.add(new Books(name,date_stored,amount));
                     //hideNoteBook();
                //hideNoteBook();98989
                        //Toast.makeText(MainActivity.this, "Hola"+ApplicationClass.book.get(i).getAmount(), Toast.LENGTH_SHORT).show();
                }
            if(ApplicationClass.book.get(0).getName().equals("Add Expense Book")){
            hideNoteBook();
            //Toast.makeText(MainActivity.this, "Hola"+am, Toast.LENGTH_SHORT).show();
            ApplicationClass.book.get(0).setAmount(am);
            }



           // lfrag.notifyChange();



        }

        //getBookLog();323
        //Toast.makeText(MainActivity.this, "Hola" + ApplicationClass.book.get(0).getName(), Toast.LENGTH_SHORT).show();



    }
    public void restoreBook(int index)
    {
        if(storedn1) {

            int amount = sharedPreferencesbooks.getInt("currentNet"+index,1);
            //ApplicationClass.book.get(index).setAmount(amount);
            /*
            ApplicationClass.restart_inside.set(index,false);
            int net = sharedPreferencesbooks.getInt("net" + index, 0);
            int tin = sharedPreferencesbooks.getInt("totalin" + index, 0);
            int tout = sharedPreferencesbooks.getInt("totalout" + index, 0);
            ApplicationClass.mBook_new.get(index).setNetBalance(net);
            ApplicationClass.mBook_new.get(index).setAmountIn(tin);
            ApplicationClass.mBook_new.get(index).setAmountout(tout);
            //hideNoteBook();98989
            int size = sharedPreferencesbooks.getInt("SizeExpenseInside", 0);
            //Toast.makeText(NoteBookDetails.this, "Clicked" + size, Toast.LENGTH_SHORT).show();
            for (int i = 0; i < size; i++) {
                String name = sharedPreferencesbooks.getString("tag"+i, "null");
                String date_stored = sharedPreferencesbooks.getString("dateInside"+i, "Select Date");
                String amount_stored = sharedPreferencesbooks.getString("amountInside"+i, "Something");
                String color = sharedPreferencesbooks.getString("color"+i, "Green");
                // expenseBook e1 = new expenseBook(tag, amount, selectedDate, color);
                //createExpense(amount_stored,name,color);
                ApplicationClass.lol2.get(index).add(new expenseBook(name, amount_stored, date_stored, color));

                //hideNoteBook();98989

            }

             */
        }


    }


    @Override
    public void onItemClicked(int index, LinearLayout linearAll, LinearLayout linearOut) {

        val = false;
        newBook=false;
        clickedIndex = index;

        /*
        if(stored && ApplicationClass.restart_inside.get(index))
        {
            restoreBook(index);
        }

         */


        if(ApplicationClass.book.get(index).getName().equals(resources.getString(R.string.AddExpenseBook)))
        {
            //hideNoteBook();
            //Toast.makeText(MainActivity.this, "Entereed" + flag, Toast.LENGTH_SHORT).show();

            showNoticeDialog();

        }


        else {

            booksize = ApplicationClass.book.size();
            ApplicationClass.setBooksize(booksize);
/*
            This One without Hash Mapping

            for (int i = 0; i < booksize; i++) {
                ApplicationClass.mBook.add(new MaintainFinalBalance(0, 0, 0, 0,ApplicationClass.book.get(index).getName()));
            }

 */
            //Toast.makeText(MainActivity.this, "Size" + ApplicationClass.mBook.size(),Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, ApplicationClass.mBook.get(index).getNetBalance(),Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, NoteBookDetails.class);
            intent.putExtra("index", String.valueOf(index));
            intent.putExtra("res", res_b);

            //Toast.makeText(MainActivity.this, "Click"+ ApplicationClass.book.get(index).getName(), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

    }

    @Override
    public void onLongMainClicked(int index) {
        mainLongClicked = true;
        val = true;
        mainLongClickedPosition = index;
        showNoticeDialog();
    }

    @Override
    public void itemsPassedfromadapter(TextView textView) {
/*
        if(condition)
            textView.setText("0");
        else{
        int i = ApplicationClass.mBook_new.get(clickedIndex).getNetBalance();
        textView.setText(String.valueOf(i));}


 */

    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");

    }

    @Override
    public void onDialogPositiveClick(BottomSheetDialog dialog, EditText etText) {
        //One Way to get the dialog values is to use this Dialog.getDialog where dialog is an instance
        //of dialog fragment we have created and passed to from there only.

        //Second method is to pass the text which was written there and use it here by passing as
        //Here
        //But this one is the best way to do so.
        if(mainLongClicked == false) {
            BottomSheetDialog dialogView = dialog;
            EditText et = (EditText) dialogView.findViewById(R.id.etText);
            name = (String) et.getText().toString();
            namesBook.add(name);
            //Add Create NoteBook Here oftherwise it will be created after clicking Add Notebook twice
            // also add notifyDatachange in createBook after data being added for the same reason
            if (name.isEmpty()) {
                Toast.makeText(MainActivity.this, resources.getString(R.string.providename), Toast.LENGTH_SHORT).show();
                showNoticeDialog();


            } else {
                createBook(name, setDate.getText().toString(), amount);
                if(ApplicationClass.book.get(clickedIndex).getName().equals(firstBook)){
                hideNoteBook();
                }

            }

        }
        else if(mainLongClicked == true)
        {
            mainLongClicked = false;
            BottomSheetDialog dialogView = dialog;
            EditText et = (EditText) dialogView.findViewById(R.id.etText);
            name = (String) et.getText().toString();
            namesBook.add(name);
            if(!name.isEmpty())
            {
                ApplicationClass.book.get(mainLongClickedPosition).setName(name);
                lfrag.notifyChange();
            }
            else if(!setDate.getText().toString().isEmpty())
            {
                ApplicationClass.book.get(mainLongClickedPosition).setDate(setDate.getText().toString());
                lfrag.notifyChange();
            }

            //Add Create NoteBook Here oftherwise it will be created after clicking Add Notebook twice
            // also add notifyDatachange in createBook after data being added for the same reason

        }
        //Here we have used the setDate parameter to set the date of what we taken input from the date


    }


    @Override
    public void onDialogNegativeClick(BottomSheetDialog dialog) {
        canceled =1;

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

    @Override
    public void onDialogDeleteClick(BottomSheetDialog dialog) {
        //Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
        //ApplicationClass.lol2.remove(mainLongClickedPosition);

        val = false;
        mainLongClicked = false;
        ApplicationClass.book.remove(mainLongClickedPosition);
        lfrag.removeItem(mainLongClickedPosition);
        //notebook.removeInsideNotebook(mainLongClickedPosition);
        //ApplicationClass.mBook_new.remove(mainLongClickedPosition);
        //Toast.makeText(MainActivity.this, "size"+ApplicationClass.book.size(),Toast.LENGTH_SHORT).show();
        //ApplicationClass.mBook_new.replace(mainLongClickedPosition,new MaintainFinalBalance(0,0,0,0,"0"));
        //ApplicationClass.mBook_new.replace(mainLongClickedPosition,ApplicationClass.mBook_new.get(mainLongClickedPosition+1));
        //ApplicationClass.lol2.replace(mainLongClickedPosition, ApplicationClass.lol2.get(mainLongClickedPosition+1));


        for(replace=0;replace<ApplicationClass.book.size(); replace++)
        {
            ApplicationClass.mBook_new.replace(mainLongClickedPosition,ApplicationClass.mBook_new.get(mainLongClickedPosition+1));
            ApplicationClass.lol2.replace(mainLongClickedPosition, ApplicationClass.lol2.get(mainLongClickedPosition+1));
        }
        ApplicationClass.mBook_new.replace(ApplicationClass.book.size(),new MaintainFinalBalance(0,0,0,0,"0"));
        ApplicationClass.lol2.replace(ApplicationClass.book.size(),  ApplicationClass.lol2.get(1999));


    }

    @Override
    public void onDialogLangSet(BottomSheetDialog dialog) {
        TextView t = dialog.findViewById(R.id.AddBookTag);
        EditText etT = dialog.findViewById(R.id.etText);
        TextView pas = dialog.findViewById(R.id.password);
        Button dB = dialog.findViewById(R.id.dataButton);
        Button cancel = dialog.findViewById(R.id.Cancel);
        Button save = dialog.findViewById(R.id.Save);
        Button delete = dialog.findViewById(R.id.Delete);
        t.setText(resources.getString(R.string.AddBookTag));
        etT.setHint(resources.getString(R.string.AddNameTag));
        pas.setText(resources.getString(R.string.SelectDateFirstTag));
        dB.setText(resources.getString(R.string.selectDateButton));
        cancel.setText(resources.getString(R.string.CancelButton));
        save.setText(resources.getString(R.string.SaveButton));
        delete.setText(resources.getString(R.string.DeleteButton));


    }

    public boolean hideDeleteButton()
    {
        return val;
    }


    public void createBook(String name, String tag, int amount)
    {
        if(!dataClicked)
        {
            tag = date;
        }
        else
        {
            dataClicked = false;
        }

        Books b1 = new Books(name, tag, amount);
        ApplicationClass.book.add(b1);
        lfrag.notifyChange();
        storeBookLog();


    }



    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
        Calendar mcalender = Calendar.getInstance();
        mcalender.set(Calendar.YEAR, i);
        mcalender.set(Calendar.MONTH, i1);
        mcalender.set(Calendar.DAY_OF_MONTH, i2);
        //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mcalender.getTime());
        String date = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).format(mcalender.getTime());
        setDate.setText(date);

    }

    @Override
    public void OnOptionClicked(ImageView iv1,ImageView iv2, ImageView iv3) {
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor.putBoolean("Book", true);
                clickColor.putBoolean("Help", false);
                clickColor.putBoolean("Setting", false);
                clickColor.apply();
                //btnAddBook.setVisibility(View.VISIBLE);
                hide_pos = 1;
                getSupportFragmentManager().beginTransaction()
                        .show(list_frag).commit();
                setTitle("Expense Book");


            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    clickColor.putBoolean("Book", false);
                    clickColor.putBoolean("Help", true);
                    clickColor.putBoolean("Setting", false);
                    clickColor.apply();
                   hide_pos=2;
                   //getSupportFragmentManager().beginTransaction().hide(list_frag).commit();
                   Intent intent = new Intent(MainActivity.this, com.example.cashbook.HelpActivity.class);
                   startActivity(intent);
                   setTitle("Expense Book");
                   //finish();
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickColor.putBoolean("Book", false);
                clickColor.putBoolean("Help", false);
                clickColor.putBoolean("Setting", true);
                clickColor.apply();

                Intent intent = new Intent(MainActivity.this, com.example.cashbook.Setting.class);
                startActivity(intent);
                //finish();

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


            if (!dataClicked) {
                Books bstart = new Books(resources.getString(R.string.AddExpenseBook), date, amount);
                ApplicationClass.book.add(bstart);
            } else {
                dataClicked = false;
                Books bstart = new Books(resources.getString(R.string.AddExpenseBook), "Select Date", amount);
                ApplicationClass.book.add(bstart);
            }



    }
    public void hideNoteBook()
    {
            ApplicationClass.book.remove(ApplicationClass.book.remove(0));
    }

    @Override
    public void onSearchList(RecyclerView.Adapter adapter) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //https://www.geeksforgeeks.org/searchview-in-android-with-recyclerview/

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView sc = (SearchView) menuItem.getActionView();
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        sc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //actionBar.setDisplayHomeAsUpEnabled(true);
                filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        sc.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                startActivity(getIntent());
                return false;
            }
        });
        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                actionBar.setBackgroundDrawable(getDrawable(R.drawable.background));

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                startActivity(getIntent());
                return false;
            }
        });
        Drawable yourdrawable = menu.getItem(0).getIcon(); // change 0 with 1,2 ...
        final PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(getColor(R.color.action), PorterDuff.Mode.SRC_ATOP);
        yourdrawable.setColorFilter(colorFilter);








        return  true;

    }

    private void filter(String text) {

        ArrayList<Books> filteredBooks =  new ArrayList<>();


        for (Books item : ApplicationClass.book) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {

                //Toast.makeText(getActivity(),""+item.getName(),Toast.LENGTH_SHORT).show();
                filteredBooks.add(item);
                //Toast.makeText(getActivity(),"Hola",Toast.LENGTH_SHORT).show();
            }
            myadapter = new cashBookAdapter(MainActivity.this, filteredBooks);
            lfrag.list.setAdapter(myadapter);


        }


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(getIntent());
                return true;
        }


        return super.onOptionsItemSelected(item);


    }
    public void setActionBarColors()
    {
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(getColor(R.color.lightColor));
        ColorDrawable textDrawable = new ColorDrawable(getColor(R.color.textColor));
        actionBar.setBackgroundDrawable(getDrawable(R.drawable.background));
        actionBar.setTitle(HtmlCompat.fromHtml("<font color="+getColor(R.color.action)+">"+resources.getString(R.string.ExpenseBook)+"</font>", HtmlCompat.FROM_HTML_MODE_LEGACY));

    }

    public void storeBookLog()
    {
        //Toast.makeText(MainActivity.this, ""+sharedPreferencesBooks.getInt("amount"+i,1), Toast.LENGTH_SHORT);
        booksEdit.putInt("Size", ApplicationClass.book.size());
        booksEdit.putBoolean("Storedn6", true);
        booksEdit.apply();
        for(int i =0; i<ApplicationClass.book.size();i++)
        {
        booksEdit.putString("Book"+String.valueOf(i),ApplicationClass.book.get(i).getName());
        booksEdit.putString("date"+String.valueOf(i), ApplicationClass.book.get(i).getDate());
        booksEdit.apply();
        }



    }
    public void storeAmount()
    {
        for(int i =0; i<ApplicationClass.book.size();i++){
            booksEdit.putInt("amount"+String.valueOf(i), ApplicationClass.book.get(i).getAmount());
            booksEdit.apply();
        }
    }

    public void getBookLog()
    {
        //stored = sharedPreferencesBooks.getBoolean("Stored1", false);

            String bookName;
            for(int i =1; i<ApplicationClass.book.size();i++){
                bookName = sharedPreferencesBooks.getString("Book"+i,"Null");
                createBook(bookName,"",0);
            }
            lfrag.notifyChange();



    }

    @Override
    protected void onResume() {
        super.onResume();

        //getBookLog();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //getBookLog();

    }
}