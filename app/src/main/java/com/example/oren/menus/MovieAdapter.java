package com.example.oren.menus;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO: implement
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        //TODO: implement
    }

    @Override
    public int getItemCount() {
        //TODO: implement
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

     //   TextView mTextView = findById(R.id.textView);


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //TODO: implement
        }
    }
}
