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
    String[] wordArr = new String[] {"ni", "wo", "shi", "na", "ta", "wo men"};
    String[] defArr = new String[] {"you", "me", "is", "that", "she/him", "we"};

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
        WordsAdapter wordsAdapter = new WordsAdapter(this, R.layout.item_view, wordList, defList);
        simpleList.setAdapter(wordsAdapter);
    }

    //cannot press back to go to login page
    @Override
    public void onBackPressed()
    {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
