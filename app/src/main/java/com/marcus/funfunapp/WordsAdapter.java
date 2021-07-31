package com.marcus.funfunapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordsAdapter extends ArrayAdapter<String>
{
    List<String> wordList = new ArrayList<>();
    List<String> defList = new ArrayList<>();

    public WordsAdapter(Context context, int textViewResourceId, List<String> wordObjects, List<String> defObjects)
    {
        super(context, textViewResourceId, wordObjects);

        wordList = wordObjects;
        defList = defObjects;
    }

    @Override
    public int getCount()
    {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //create inflater
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_view, null);

        //set word and def in their respective textViews
        TextView wordView = (TextView) v.findViewById(R.id.wordView);
        wordView.setText(wordList.get(position));

        TextView defView = (TextView) v.findViewById(R.id.defView);
        defView.setText(defList.get(position));

        return v;
    }
}
