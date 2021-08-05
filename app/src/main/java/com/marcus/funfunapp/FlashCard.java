package com.marcus.funfunapp;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FlashCard extends AppCompatActivity {

    AnimatorSet mSetRightOut;
    AnimatorSet mSetLeftIn;
    boolean mIsBackVisible = false;
    View mCardFrontLayout;
    View mCardBackLayout;
    Button rightButton, leftButton;
    TextView back, front;
    String[] checkedWords;
    String[] checkedDefs;
    int currentNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard);
        initChecked();
        findViews();
        loadAnimations();
        initButtons();
        changeCameraDistance();
    }

    private void changeCameraDistance()
    {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations()
    {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
    }

    private void findViews()
    {
        front = (TextView) findViewById(R.id.flashcard_word);
        front.setText(getCurrentWord());
        mCardFrontLayout = findViewById(R.id.card_front);
        back = (TextView) findViewById(R.id.flashcard_def);
        back.setText(getCurrentDef());
        mCardBackLayout = findViewById(R.id.card_back);
    }

    public void flipCard(View view)
    {
        if (!mIsBackVisible)
        {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        }
        else
        {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }

    private void initButtons()
    {
        rightButton = findViewById(R.id.flashcard_button_right);
        rightButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (currentNum < checkedWords.length - 1)
                {
                    currentNum++;
                    front.setText(getCurrentWord());
                    back.setText(getCurrentDef());
                }
            }
        });

        leftButton = findViewById(R.id.flashcard_button_left);
        leftButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (currentNum > 0)
                {
                    currentNum--;
                    front.setText(getCurrentWord());
                    back.setText(getCurrentDef());
                }
            }
        });
    }

    private void initChecked()
    {
        Intent intent = getIntent();
        ArrayList<Integer> checkedNums = intent.getIntegerArrayListExtra("checked");

        checkedWords = new String[checkedNums.size()];
        checkedDefs = new String[checkedNums.size()];

        VarHolder varHolder = new VarHolder();
        String[] wordList = varHolder.getWords();
        String[] defList = varHolder.getDefs();

        for (int i = 0; i < checkedNums.size(); i++)
        {
            int checkedNum = checkedNums.get(i);
            checkedWords[i] = wordList[checkedNum];
            checkedDefs[i] = defList[checkedNum];
        }
    }

    private String getCurrentWord()
    {
        return checkedWords[currentNum];
    }

    private String getCurrentDef()
    {
        return checkedDefs[currentNum];
    }
}

