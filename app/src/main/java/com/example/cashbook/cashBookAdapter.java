package com.example.cashbook;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cashBookAdapter extends RecyclerView.Adapter<cashBookAdapter.ViewHolder> {

    ArrayList<Books> book;
    ItemClicked activity;
    public interface ItemClicked
    {
        void onItemClicked(int index, CardView cardView);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            fileNameText = itemView.findViewById(R.id.fileNameText);
            fileDateText = itemView.findViewById(R.id.fileDataText);
            mainCardView = itemView.findViewById(R.id.mainCardView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                            //view.setBackgroundColor(Color.GREEN);
                            activity.onItemClicked(book.indexOf((Books)view.getTag()),mainCardView);

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
        holder.itemView.setTag(book.get(position));
        holder.fileDateText.setText(book.get(position).getDate());
        holder.fileNameText.setText(book.get(position).getName());
        }

    @Override
    public int getItemCount() {
        return book.size();
    }
}
