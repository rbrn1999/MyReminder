package com.example.android.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.LinkedList;

public class ItemAdapter extends
        RecyclerView.Adapter<ItemAdapter.SubViewHolder> {
    private static final String TAG = "ItemAdapter";
    private final LinkedList<String> mList;
    private final LayoutInflater mInflater;


    public ItemAdapter(Context context, LinkedList<String> mItemList) {
        mInflater = LayoutInflater.from(context);
        this.mList = mItemList;
    }

    class SubViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final EditText wordItemText;
        final ItemAdapter mAdapter;
        private final LinkedList<String> mItemList;

        public SubViewHolder(View itemView, ItemAdapter adapter, LinkedList<String> mItemList) {
            super(itemView);
            this.mItemList = mItemList;
            Log.d(TAG, "ViewHolder: created");
            wordItemText = itemView.findViewById(R.id.item_text);
            this.mAdapter = adapter;
        }

        @Override
        public void onClick(View v) {

        }
    }


    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(
                R.layout.sublist_item, parent, false);
        return new SubViewHolder(mItemView, this, this.mList);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder holder, int position) {
//        holder.wordItemText.setText("new item");
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

}
