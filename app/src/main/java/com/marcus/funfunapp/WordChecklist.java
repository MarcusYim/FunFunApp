package com.marcus.funfunapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class WordChecklist extends AppCompatActivity
{
    ListView simpleListView;
    String[] StringArray = new String[] {"abac", "sunday", "collide", "samantha", "liquor", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_checklist);

        simpleListView = (ListView) findViewById(R.id.simpleListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_view, R.id.itemTextView, StringArray);
        simpleListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed()
    {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
