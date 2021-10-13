package com.marcus.funfunapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class LevelAdapter extends ArrayAdapter<String>
{
    List<String> imageList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    List<Drawable> drawableList = new ArrayList<>();
    boolean[][] starredArr;
    VarHolder varHolder;
    Context mContext;


    public LevelAdapter(Context context, int textViewResourceId, List<String> imageObjects, List<String> nameObjects, List<Drawable> drawableObjects, boolean[][] starredObjects)
    {
        super(context, textViewResourceId, imageObjects);

        mContext = context;

        imageList = imageObjects;
        nameList = nameObjects;
        drawableList = drawableObjects;
        varHolder = new VarHolder(true);
        starredArr = varHolder.getStarredArr();
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

            holder = new ViewHolder(v);

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

        holder.levelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int previous = varHolder.getNumPreviousDialogues(position);
                int curr = varHolder.getDialoguesPerLevel(position);

                Intent intent = new Intent(v.getContext(), WordChecklist.class);
                Bundle bundle = new Bundle();
                intent.putExtra("start", previous);
                intent.putExtra("end", previous + curr);
                intent.putExtra("starred", starredArr);
                intent.putExtra("dialogues", varHolder.getSubDialogueArray(previous, previous + curr));


                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                v.getContext().startActivity(intent);
            }
        });

        return v;
    }
}