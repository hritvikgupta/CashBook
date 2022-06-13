package com.example.cashbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cashBookAdapter extends RecyclerView.Adapter<cashBookAdapter.ViewHolder> {

    ArrayList<Books> book;
    ItemClicked activity;
    Boolean darkon;

    public interface ItemClicked
    {
        void onItemClicked(int index, LinearLayout linearAll, LinearLayout linearLayoutOut);
        void onLongMainClicked(int index);
    }



    public cashBookAdapter(Context context, ArrayList<Books> book)
    {
        this.book = book;
        activity = (ItemClicked) context;


    }
    public void updateList(ArrayList<Books> list){
       this.book= list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView fileNameText, fileDateText;
        CardView mainCardView;;
        LinearLayout linearAll, LinearOut;
        SharedPreferences sharedPreferences;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sharedPreferences = itemView.getContext().getSharedPreferences("SP",Context.MODE_PRIVATE);
            darkon  =sharedPreferences.getBoolean("darkon",false);
            fileNameText = itemView.findViewById(R.id.fileNameText);
            fileDateText = itemView.findViewById(R.id.fileDataText);
            mainCardView = itemView.findViewById(R.id.mainCardView);
            linearAll = itemView.findViewById(R.id.linearAll);
            LinearOut  = itemView.findViewById(R.id.linearOUt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                            //view.setBackgroundColor(Color.GREEN);
                            activity.onItemClicked(book.indexOf((Books)view.getTag()),linearAll,LinearOut);

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    activity.onLongMainClicked(getAdapterPosition());
                    return false;
                }
            });



        }
    }

    @NonNull
    @Override
    public cashBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cashBookAdapter.ViewHolder holder, int position) {
        if(darkon)
        {
            holder.linearAll.setBackgroundColor(Color.parseColor("#141e30"));
            holder.LinearOut.setBackgroundColor(Color.parseColor("#141e30"));
        }
        else{
        holder.linearAll.setBackgroundColor(Color.parseColor("#ebedee"));
        holder.LinearOut.setBackgroundColor(Color.parseColor("#ebedee"));}
        holder.itemView.setTag(book.get(position));
        holder.fileDateText.setText(book.get(position).getDate());
        holder.fileNameText.setText(book.get(position).getName());
        }

    @Override
    public int getItemCount() {
        return book.size();
    }
}
