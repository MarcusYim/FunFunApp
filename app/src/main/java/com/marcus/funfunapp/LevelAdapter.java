package com.marcus.funfunapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LevelAdapter extends ArrayAdapter<String>
{
    List<String> imageList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    List<Drawable> drawableList = new ArrayList<>();

    public LevelAdapter(Context context, int textViewResourceId, List<String> imageObjects, List<String> nameObjects, List<Drawable> drawableObjects)
    {
        super(context, textViewResourceId, imageObjects);

        imageList = imageObjects;
        nameList = nameObjects;
        drawableList = drawableObjects;
    }

    @Override
    public int getCount()
    {
            return imageList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        ViewHolder holder;

        if (v == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.level_view, null);

            holder = new ViewHolder();

            holder.levelButton = (Button) v.findViewById(R.id.level_button);
            holder.levelText = (TextView) v.findViewById(R.id.level_text);
            holder.levelImage = (ImageView) v.findViewById(R.id.level_image);

            v.setTag(holder);
        }
        else
        {
            // view already exists, get the holder instance from the view
            holder = (ViewHolder) v.getTag();
        }

        holder.levelText.setText(nameList.get(position));
        holder.levelImage.setImageDrawable(drawableList.get(position));

        return v;
    }
}