package com.example.android.recyclerview;

import java.util.LinkedList;

public class Data {
    public String category;
    public LinkedList<String> itemList;

    public Data() {
        category = "";
        itemList = new LinkedList<>();
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
