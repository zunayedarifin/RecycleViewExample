package com.example.arifin.recycleviewexample;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private List<RecyclerViewCustomClass> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    RecycleViewAdapter(Context context, List<RecyclerViewCustomClass> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleviewitems, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        for(int i=0;i<getItemCount();i++){
            String s1 = mData.get(position).getValue1();
            String s2 = mData.get(position).getValue2();
            String s3 = mData.get(position).getValue3();
            holder.myTextView.setText(s1);
            holder.myTextView2.setText(s2);
            holder.myTextView3.setText(s3);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView,myTextView2,myTextView3;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            myTextView2= itemView.findViewById(R.id.tvAnimalName2);
            myTextView3= itemView.findViewById(R.id.tvAnimalName3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    //convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id).toString();
    }

    //allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
