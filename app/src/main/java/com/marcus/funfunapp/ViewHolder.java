package com.marcus.funfunapp;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder
{
    ImageView levelImage;
    TextView levelText;
    Button levelButton;

    public ViewHolder(View itemView)
    {
        super(itemView);
        Context context = itemView.getContext();
    }
}
