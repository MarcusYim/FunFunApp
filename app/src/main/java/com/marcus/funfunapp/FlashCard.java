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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    int endDialogue;
    int startDialogue;
    ArrayList<Integer> checkedNums;

    List<String>[] allWords;
    List<String>[] allEnglish;
    List<String>[] allPin;

    List<String> allWordsList = new ArrayList<>();
    List<String> allEnglishList = new ArrayList<>();
    List<String> allPinList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard);

        Bundle extras = getIntent().getExtras();
        startDialogue = extras.getInt("start");
        endDialogue = extras.getInt("end");
        checkedNums = extras.getIntegerArrayList("checked");

        parseCsv();
        combineAllWords();
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
                randomizeArrays(checkedWords, checkedDefs, checkedPins);
                front.setText(getCurrentWord());
                back.setText(getCurrentDef());
                pin.setText(getCurrentPin());
            }
        });
    }

    private void initChecked()
    {
        Intent intent = getIntent();

        checkedWords = new String[checkedNums.size()];
        checkedDefs = new String[checkedNums.size()];
        checkedPins = new String[checkedNums.size()];

        for (int i = 0; i < checkedNums.size(); i++)
        {
            int checkedNum = checkedNums.get(i);
            checkedWords[i] = allWordsList.get(checkedNum);
            checkedDefs[i] = allEnglishList.get(checkedNum);
            checkedPins[i] = allPinList.get(checkedNum);
        }
    }

    private void parseCsv()
    {
        allWords = new List[endDialogue - startDialogue];
        allEnglish = new List[endDialogue - startDialogue];
        allPin = new List[endDialogue - startDialogue];

        int count = 0;
        for (int i = startDialogue; i < endDialogue; i++)
        {
            try
            {
                String uriC = "@raw/dialogue_" + i + "_chinese";
                int csvResourceC = getResources().getIdentifier(uriC, null, getPackageName());
                CSVReader readerC = new CSVReader(new InputStreamReader(getResources().openRawResource(csvResourceC)));
                allWords[count] = Arrays.asList(readerC.readNext());

                String uriE = "@raw/dialogue_" + i + "_english";
                int csvResourceE = getResources().getIdentifier(uriE, null, getPackageName());
                CSVReader readerE = new CSVReader(new InputStreamReader(getResources().openRawResource(csvResourceE)));
                allEnglish[count] = Arrays.asList(readerE.readNext());

                String uriP = "@raw/dialogue_" + i + "_pin";
                int csvResourceP = getResources().getIdentifier(uriP, null, getPackageName());
                CSVReader readerP = new CSVReader(new InputStreamReader(getResources().openRawResource(csvResourceP)));
                allPin[count] = Arrays.asList(readerP.readNext());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(this, "Error in file reading", Toast.LENGTH_SHORT).show();
            }

            count++;
        }
    }

    private void combineAllWords()
    {
        for (List<String> list : allWords)
        {
            allWordsList.addAll(list);
        }

        for (List<String> list : allEnglish)
        {
            allEnglishList.addAll(list);
        }

        for (List<String> list : allPin)
        {
            allPinList.addAll(list);
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

    private void randomizeArrays(String[] word, String[] def, String[] pin)
    {
        Random rgen = new Random();

        for (int i = 0; i < word.length; i++)
        {
            int randomPosition = rgen.nextInt(word.length);
            String temp = word[i];
            word[i] = word[randomPosition];
            word[randomPosition] = temp;

            String temp1 = def[i];
            def[i] = def[randomPosition];
            def[randomPosition] = temp1;

            String temp2 = pin[i];
            pin[i] = pin[randomPosition];
            pin[randomPosition] = temp2;
        }
    }
}

