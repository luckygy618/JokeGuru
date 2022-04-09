package com.guoyucao.jokeguru;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.MyViewHolder> {

    private ArrayList<Joke> jokeList;
    private Context mContext;
    private ItemListener mItemListener;

    public interface ItemListener {
        void onItemClicked(Joke item, int position);
    }

    public JokeAdapter(Context context,ArrayList<Joke> itemLst) {
        this.jokeList = itemLst;
        mContext = context;
    }

    public void setItemListener(ItemListener mItemListener) {
        this.mItemListener = mItemListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.joke_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.row_id.setText("ID: " + jokeList.get(position).getId());
        holder.row_tag.setText("Tag: "+jokeList.get(position).getCategory());
        holder.row_detail.setText(jokeList.get(position).getJoke().substring(0,27) + " ...");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemListener != null){
                    mItemListener.onItemClicked(jokeList.get(holder.getAdapterPosition()),holder.getAdapterPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return jokeList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView row_id, row_tag, row_detail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_id = itemView.findViewById(R.id.joke_row_id);
            row_tag = itemView.findViewById(R.id.joke_row_tag);
            row_detail = itemView.findViewById(R.id.joke_row_detail);

        }

        @Override
        public void onClick(View v) {
        }
    }

}
