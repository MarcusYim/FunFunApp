package com.marcus.funfunapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    Button studyStarred;
    boolean[][] starredArr;
    Long[] purchased;
    VarHolder varHolder;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_select);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        purchased = (Long[]) (bundle.getSerializable("purchased"));

        varHolder = new VarHolder();
        varHolder.initStarredArray();
        starredArr = varHolder.getStarredArr();
        varHolder.initStarredArray();

        initVars();
        initCustomView();
        initButtons();
    }

    private void initVars()
    {
        imageArr = varHolder.getLevelImages();
        nameArr = varHolder.getLevelNames();

        for (int i = 0; i < varHolder.getNumImages(); i++)
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
        levelAdapter = new LevelAdapter(this, R.layout.item_view, imageList, nameList, drawableList, purchased);
        levelSelect.setAdapter(levelAdapter);
    }

    private void initButtons()
    {
        studyStarred = (Button) findViewById(R.id.level_button_starred);

        studyStarred.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ArrayList<Integer> temp = varHolder.getStarredChecked();

                if (temp.size() > 0)
                {
                    Intent intent = new Intent(v.getContext(), FlashCard.class);
                    intent.putExtra("checked", temp);
                    intent.putExtra("end", starredArr.length);
                    intent.putExtra("start", 0);
                    startActivity(intent);
                }
            }
        });
    }
}
