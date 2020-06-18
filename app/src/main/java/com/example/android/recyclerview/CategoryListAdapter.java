/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

/**
 * Shows how to implement a simple Adapter for a RecyclerView.
 * Demonstrates how to add a click handler for each item in the ViewHolder.
 */
public class CategoryListAdapter extends
        RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {
    private static final String TAG = "CategoryListAdapter";

    private final LinkedList<Data> mDataList;
    private final LayoutInflater mInflater;
    private Context context;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private ItemListAdapter mItemListAdapter;
//    private LinearLayoutManager itemLayoutManager = new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
    class CategoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final EditText wordItemView;
        final CategoryListAdapter mAdapter;
        private RecyclerView mItemRecyclerView;
        private LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        private RecyclerView.RecycledViewPool recycledViewPool;
        private final LinkedList<String> mItemList = new LinkedList<>();


        /**
         * Creates a new custom view holder to hold the view to display in
         * the RecyclerView.
         *
         * @param itemView The view in which to display the data.
         * @param adapter The adapter that manages the the data and views
         *                for the RecyclerView.
         */
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
//            // Create an adapter and supply the data to be displayed.
//            mItemAdapter = new ItemAdapter(context, mItemList);
//            // Connect the adapter with the recycler view.
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

//            mCategoryList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }

    }

    public CategoryListAdapter(Context context, LinkedList<Data> dataList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mDataList = dataList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to
     * represent an item.
     *
     * This new ViewHolder should be constructed with a new View that can
     * represent the items of the given type. You can either create a new View
     * manually or inflate it from an XML layout file.
     *
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder(ViewHolder, int, List). Since it will be reused to
     * display different items in the data set, it is a good idea to cache
     * references to sub views of the View to avoid unnecessary findViewById()
     * calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after
     *                 it is bound to an adapter position.
     * @param viewType The view type of the new View. @return A new ViewHolder
     *                 that holds a View of the given view type.
     */

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.categotylist_item, parent, false);
        return new CategoryViewHolder(mItemView, this);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the ViewHolder.itemView to
     * reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent
     *                 the contents of the item at the given position in the
     *                 data set.
     * @param position The position of the item within the adapter's data set.
     */
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

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
