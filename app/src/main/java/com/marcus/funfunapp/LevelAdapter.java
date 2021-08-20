package com.marcus.funfunapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LevelAdapter extends ArrayAdapter<String>
{
    List<String> levelList = new ArrayList<>();
    String[] imageList;

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
        v = inflater.inflate(R.layout.level_view, null);

        TextView levelText = (TextView) v.findViewById(R.id.level_text);
        levelText.setText(levelList.get(position));


        File imgFile = new  File("res/drawable/level_" + (position + 1) + "_image.png");

        if (imgFile.exists())
        {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView levelImage = (ImageView) v.findViewById(R.id.level_image);

            levelImage.setImageBitmap(myBitmap);
        }

        return v;
    }
}
