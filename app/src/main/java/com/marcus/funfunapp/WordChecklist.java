package com.marcus.funfunapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class WordChecklist extends AppCompatActivity
{
    ListView simpleListView;
    String[] wordArr = new String[] {"你", "我", "是", "那", "他", "我们"};
    String[] defArr = new String[] {"you", "me", "is", "that", "him", "we"};

    WordsAdapter wordsAdapter;

    //defining page to display on
    ListView simpleList;
    //defining elements to be displayed
    ArrayList<String> wordList = new ArrayList<String>();
    ArrayList<String> defList = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_checklist);

        //change this
        wordList.addAll(Arrays.asList(wordArr));
        defList.addAll(Arrays.asList(defArr));

        //identify page to display on
        simpleList = (ListView) findViewById(R.id.simpleListView);

        //create and set custom adapter
        wordsAdapter = new WordsAdapter(this, R.layout.item_view, wordList, defList);
        simpleList.setAdapter(wordsAdapter);
    }

    //cannot press back to go to login page
    @Override
    public void onBackPressed()
    {

    }
}
