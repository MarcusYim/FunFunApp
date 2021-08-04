package com.marcus.funfunapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FlashCard extends AppCompatActivity
{
    String[] wordList;
    String[] defList;
    ArrayList<Integer> checkedNums;
    String[] checkedWords;
    String[] checkedDefs;
    boolean wordSide;
    int cardNum = 0;
    Button rightButton, leftButton;

    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_word);

        VarHolder var = new VarHolder();
        wordList = var.getWords();
        defList = var.getDefs();

        Intent intent = getIntent();
        checkedNums = intent.getIntegerArrayListExtra("checked");
        if (checkedNums == null)
        {
            checkedNums = new ArrayList<Integer>();
        }

        wordSide = true;

        loadChecked();

        TextView word = (TextView) findViewById(R.id.text_word);
        word.setText(checkedWords[cardNum]);

        rightButton = (Button) findViewById(R.id.flashcard_right_button);
        rightButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (cardNum < checkedWords.length)
                {
                    cardNum++;

                    setContentView(R.layout.flashcard_word);
                    TextView word = (TextView) findViewById(R.id.text_word);
                    word.setText(checkedDefs[cardNum]);
                    wordSide = true;
                }
            }
        });

        leftButton = (Button) findViewById(R.id.flashcard_left_button);
        leftButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (cardNum > 0)
                {
                    cardNum--;

                    setContentView(R.layout.flashcard_word);
                    TextView word = (TextView) findViewById(R.id.text_word);
                    word.setText(checkedWords[cardNum]);
                    wordSide = true;
                }
            }
        });
    }

    private void loadChecked()
    {
        checkedWords = new String[checkedNums.size()];
        checkedDefs = new String[checkedNums.size()];

        for (int i = 0; i < checkedNums.size(); i++)
        {
            int checkedIndex = checkedNums.get(i);
            checkedWords[checkedIndex] = wordList[checkedIndex];
            checkedDefs[checkedIndex] = defList[checkedIndex];
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if (wordSide)
            {
                setContentView(R.layout.flashcard_def);
                TextView def = (TextView) findViewById(R.id.text_def);
                def.setText(checkedDefs[cardNum]);
                wordSide = false;
            }
            else
            {
                setContentView(R.layout.flashcard_word);
                TextView word = (TextView) findViewById(R.id.text_word);
                word.setText(checkedWords[cardNum]);
                wordSide = true;
            }
        }

        return super.onTouchEvent(event);
    }
}
