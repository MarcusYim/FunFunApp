package com.marcus.funfunapp;

import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class WordsAdapter extends ArrayAdapter<Item>
{
    ArrayList<Item> wordList = new ArrayList<>();
    ArrayList<Item> defList = new ArrayList<>();

    public WordsAdapter(Context context, int resource, int textViewResourceId, List wordObjects, List defObjects)
    {
        super(context, resource, textViewResourceId, objects);

        wordList = wordObjects;
    }

    @Override
    public int getCount()
    {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return super.getView(position, convertView, parent);
    }
}
