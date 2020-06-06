package com.example.android.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class ItemAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
