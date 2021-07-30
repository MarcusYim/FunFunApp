package com.marcus.funfunapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AddProduct extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        //sets screen to add_product screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_checklist);
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
