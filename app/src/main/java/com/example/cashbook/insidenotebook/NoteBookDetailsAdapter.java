package com.example.cashbook.insidenotebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashbook.R;

import java.util.ArrayList;

public class NoteBookDetailsAdapter extends RecyclerView.Adapter<NoteBookDetailsAdapter.ViewHolder>{

    ArrayList<expenseBook> localdataset;
    //private expenseBook [] localdataset;

    public NoteBookDetailsAdapter( ArrayList<expenseBook> eBook)
    {
        localdataset = eBook;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView eName, eAmount,eTag,totalbalanceremain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eName = itemView.findViewById(R.id.expenseName);
            eAmount = itemView.findViewById(R.id.expenseAmount);
            eTag = itemView.findViewById(R.id.expenseTag);
            totalbalanceremain = itemView.findViewById(R.id.totalBalanceRemain);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
    @NonNull
    @Override
    public NoteBookDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout_notebook_details,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteBookDetailsAdapter.ViewHolder holder, int position) {
        //holder.itemView.setTag(eBook.get(position));

        holder.eName.setText(localdataset.get(position).geteName());
        holder.eTag.setText(localdataset.get(position).geteTag());
        holder.eAmount.setText(localdataset.get(position).geteAmount());
        holder.totalbalanceremain.setText(localdataset.get(position).getTotalbalanceremain());




    }

    @Override
    public int getItemCount() {
        return localdataset.size();
    }
}
