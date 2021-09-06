package com.marcus.funfunapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelSelect extends AppCompatActivity
{
    String[] imageArr;
    String[] nameArr;
    ArrayList<String> imageList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    List<Drawable> drawableList = new ArrayList<>();
    Button levelButton;
    ImageView levelImage;
    TextView levelText;
    ListView levelSelect;
    LevelAdapter levelAdapter;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_select);

        initVars();
        initCustomView();
    }

    private void initVars()
    {
        VarHolder var = new VarHolder();
        imageArr = var.getLevelImages();
        nameArr = var.getLevelNames();

        for (int i = 0; i < var.getNumImages(); i++)
        {
            String uri = "@drawable/level_" + (i + 1) + "_image";
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            drawableList.add(res);
        }
    }

    private void initCustomView()
    {
        //change this
        imageList.addAll(Arrays.asList(imageArr));
        nameList.addAll(Arrays.asList(nameArr));


        //identify page to display on
        levelSelect = (ListView) findViewById(R.id.level_list_view);

        //create and set custom adapter
        levelAdapter = new LevelAdapter(this, R.layout.item_view, imageList, nameList, drawableList);
        levelSelect.setAdapter(levelAdapter);
    }
}
