package com.marcus.funfunapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

public class LevelAdapter extends ArrayAdapter<String>
{
    List<String> levelList = new ArrayList<>();

    public LevelAdapter(Context context, int textViewResourceId, List<String> levelObjects)
    {
        super(context, textViewResourceId, levelObjects);

        levelList = levelObjects;
    }

    @Override
    public int getCount()
    {
            return levelList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //create inflater
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_view, null);

        return v;
    }
}
