package com.example.android.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.LinkedList;

public class CategoryListAdapter extends
        RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {
    private static final String TAG = "CategoryListAdapter";

    private final LinkedList<Data> mDataList;
    private final LayoutInflater mInflater;
    private Context context;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private ItemListAdapter mItemListAdapter;
    class CategoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final EditText wordItemView;
        final CategoryListAdapter mAdapter;
        private RecyclerView mItemRecyclerView;
        private LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        private RecyclerView.RecycledViewPool recycledViewPool;
        private final LinkedList<String> mItemList = new LinkedList<>();

        public CategoryViewHolder(View itemView, CategoryListAdapter adapter) {
            super(itemView);
            Log.d(TAG, "ViewHolder: created");
            wordItemView = itemView.findViewById(R.id.category);
            this.mAdapter = adapter;
            ImageButton newBtn = (ImageButton) itemView.findViewById(R.id.new_button);
           newBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Log.d(TAG, "new button tapped");
                   int itemListSize = mItemList.size();
                   // Add a new word to the wordList.
                   mItemList.addLast("new Item");
                   // Notify the adapter, that the data has changed.
                   mItemRecyclerView.getAdapter().notifyItemInserted(itemListSize);
                   // Scroll to the bottom.
                   mItemRecyclerView.smoothScrollToPosition(itemListSize);
               }
           });

            // Create recycler view.
            mItemRecyclerView = itemView.findViewById(R.id.item_recyclerview);
            mItemListAdapter = new ItemListAdapter(context, mItemList);
            // Connect the adapter with the recycler view.
            mItemRecyclerView.setAdapter(mItemListAdapter);
            // Give the recycler view a default layout manager.
            mItemRecyclerView.setLayoutManager(layoutManager);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in mWordList.
            Data element = mDataList.get(mPosition);
            // Change the word in the mWordList.
            mAdapter.notifyDataSetChanged();
        }

    }

    public CategoryListAdapter(Context context, LinkedList<Data> dataList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mDataList = dataList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.categotylist_item, parent, false);
        return new CategoryViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        String mCurrent = mDataList.get(position).category;
        // Add the data to the view holder.
        holder.wordItemView.setText(mCurrent);
        holder.mItemRecyclerView.setAdapter(mItemListAdapter);
        holder.mItemRecyclerView.setRecycledViewPool(recycledViewPool);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
