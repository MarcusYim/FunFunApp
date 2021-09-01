package com.marcus.funfunapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class WordChecklist extends AppCompatActivity
{
    ListView simpleListView;
    String[] wordArr, defArr, pinArr;

    WordsAdapter wordsAdapter;

    //defining page to display on
    ListView simpleList;
    Button studyButton;
    //defining elements to be displayed
    ArrayList<String> wordList = new ArrayList<String>();
    ArrayList<String> defList = new ArrayList<String>();
    ArrayList<String> pinList = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_checklist);

        initCustomView();
        initDropdownMenu();
    }

    private void initCustomView()
    {
        VarHolder var = new VarHolder();
        wordArr = var.getWords();
        defArr = var.getDefs();
        pinArr = var.getPins();

        //change this
        wordList.addAll(Arrays.asList(wordArr));
        defList.addAll(Arrays.asList(defArr));
        pinList.addAll(Arrays.asList(pinArr));

        //identify page to display on
        simpleList = (ListView) findViewById(R.id.simple_list_view);

        //create and set custom adapter
        wordsAdapter = new WordsAdapter(this, R.layout.item_view, wordList, defList, pinList);
        simpleList.setAdapter(wordsAdapter);

        studyButton = findViewById(R.id.study_button);

        studyButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ArrayList<Integer> temp = getChecked();
                Intent intent = new Intent(v.getContext(), FlashCard.class);
                intent.putExtra("checked", temp);
                startActivity(intent);
            }
        });
    }

    private void initDropdownMenu()
    {
        String[] lessons = getResources().getStringArray(R.array.level1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.drop_menu_view, lessons);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    private ArrayList<Integer> getChecked()
    {
        return wordsAdapter.getChecked();
    }
}
