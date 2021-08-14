package com.marcus.funfunapp;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class FlashCard extends AppCompatActivity {

    AnimatorSet mSetRightOut;
    AnimatorSet mSetLeftIn;
    boolean mIsBackVisible = false;
    View mCardFrontLayout;
    View mCardBackLayout;
    ImageButton rightButton, leftButton;
    Button shuffleButton;
    TextView back, front, pin, curr, total;
    String[] checkedWords;
    String[] checkedDefs;
    String[] checkedPins;
    int currentNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard);
        initChecked();
        initViews();
        initAnimations();
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

    private void initAnimations()
    {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
    }

    private void initViews()
    {
        front = (TextView) findViewById(R.id.flashcard_word);
        front.setText(getCurrentWord());
        mCardFrontLayout = findViewById(R.id.card_front);
        back = (TextView) findViewById(R.id.flashcard_def);
        back.setText(getCurrentDef());
        mCardBackLayout = findViewById(R.id.card_back);
        pin = (TextView) findViewById((R.id.flashcard_pin));
        pin.setText(getCurrentPin());
        curr = (TextView) findViewById(R.id.flashcard_number);
        curr.setText("1");
        total = (TextView) findViewById(R.id.flashcard_total);
        total.setText(getResources().getString(R.string.integer_to_string, checkedWords.length));
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
                    pin.setText(getCurrentPin());
                    curr.setText(getResources().getString(R.string.integer_to_string, (currentNum + 1)));
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
                    pin.setText(getCurrentPin());
                    curr.setText(getResources().getString(R.string.integer_to_string, (currentNum + 1)));
                }
            }
        });

        shuffleButton = findViewById(R.id.flashcard_button_shuffle);
        shuffleButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                randomizeArray(checkedWords);
                randomizeArray(checkedDefs);
                randomizeArray(checkedPins);
                front.setText(getCurrentWord());
                back.setText(getCurrentDef());
                pin.setText(getCurrentPin());
            }
        });
    }

    private void initChecked()
    {
        Intent intent = getIntent();
        ArrayList<Integer> checkedNums = intent.getIntegerArrayListExtra("checked");

        checkedWords = new String[checkedNums.size()];
        checkedDefs = new String[checkedNums.size()];
        checkedPins = new String[checkedNums.size()];

        VarHolder varHolder = new VarHolder();
        String[] wordList = varHolder.getWords();
        String[] defList = varHolder.getDefs();
        String[] pinList = varHolder.getPins();

        for (int i = 0; i < checkedNums.size(); i++)
        {
            int checkedNum = checkedNums.get(i);
            checkedWords[i] = wordList[checkedNum];
            checkedDefs[i] = defList[checkedNum];
            checkedPins[i] = pinList[checkedNum];
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

    private String getCurrentPin()
    {
        return checkedPins[currentNum];
    }

    private String[] randomizeArray(String[] array)
    {
        Random rgen = new Random();

        for (int i = 0; i < array.length; i++)
        {
            int randomPosition = rgen.nextInt(array.length);
            String temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
}

