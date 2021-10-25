package com.marcus.funfunapp;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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


import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class FlashCard extends AppCompatActivity {

    AnimatorSet mSetRightOut;
    AnimatorSet mSetLeftIn;
    boolean mIsBackVisible = false;
    View mCardFrontLayout;
    View mCardBackLayout;
    ImageButton rightButton, leftButton;
    Button shuffleButton, audioButton;
    TextView back, front, pin, curr, total;
    ImageView star;

    //array that the flashcards iterate through
    String[] checkedWords;
    String[] checkedDefs;
    String[] checkedPins;
    int currentNum = 0;

    //stores the number of the first dialogue and last dialogue
    int endDialogue;
    int startDialogue;
    //arraylist of each character's number in relation to the start dialogue
    //so if "0" was in this list, that would be the first character in the startdialogue
    //not in relation to all the dialogues. "0" != first character in the first dialogue
    ArrayList<Integer> checkedNums;
    ArrayList<Integer> starredNums;

    //this contains the words/english/pin in the dialogue format
    //allWords[0] is the words in the startdialogue
    List<String>[] allWords;
    List<String>[] allEnglish;
    List<String>[] allPin;

    //amalgamated list of all dialogues startdialogue-enddialogue
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
        starredNums = extras.getIntegerArrayList("starred");

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

    //generate media players from mp3
    private void initMediaPlayers()
    {
        try
        {
            for (int i = 0; i < checkedNums.size(); i++)
            {
                /*
                //String uriA = "@raw/audio_" + countPreviousDialogues + checkedNums[i];
                String uriA = "@raw/red_spy_in_base";
                int audioResource = getResources().getIdentifier(uriA, null, getPackageName());
                players[i] = MediaPlayer.create(this, audioResource);

                 */
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Error in audio file reading", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "ERROR, FILE READ STOPPED");
        }
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

    //animations
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
        //button to move right
        rightButton = findViewById(R.id.flashcard_button_right);
        rightButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (currentNum < checkedWords.length - 1)
                {
                    //current num is the current card number
                    currentNum++;
                    front.setText(getCurrentWord());
                    System.out.println(getHashedChinese(getCurrentWord()));
                    back.setText(getCurrentDef());
                    pin.setText(getCurrentPin());
                    curr.setText(getResources().getString(R.string.integer_to_string, (currentNum + 1)));
                }
            }
        });

        //button to move left
        leftButton = findViewById(R.id.flashcard_button_left);
        leftButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (currentNum > 0)
                {
                    currentNum--;
                    front.setText(getCurrentWord());
                    System.out.println(getHashedChinese(getCurrentWord()));
                    back.setText(getCurrentDef());
                    pin.setText(getCurrentPin());
                    curr.setText(getResources().getString(R.string.integer_to_string, (currentNum + 1)));
                }
            }
        });

        //shuffle button
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

        //audio button
        audioButton = findViewById(R.id.flashcard_button_play_audio);
        audioButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String uriA = "@raw/aud_hashed_" + getHashedChinese(getCurrentWord());
                int audioResource = getResources().getIdentifier(uriA, null, getPackageName());
                MediaPlayer temp = MediaPlayer.create(v.getContext(), audioResource);
                temp.start();
            }
        });
    }

    //creating the array that the flashcards will go through
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

    //doesnt parse CSV anymore, just gets arrays from varHolder
    private void parseCsv()
    {
        VarHolder varHolder = new VarHolder();

        allWords = varHolder.getSubChineseArray(endDialogue, startDialogue);
        allEnglish = varHolder.getSubEnglishArray(endDialogue, startDialogue);
        allPin = varHolder.getSubPinArray(endDialogue, startDialogue);
    }

    //probably a bad implementation
    //don't have to copy everything to a list just to copy it to an array
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

    //methods used to get words/defs/pins at the current flashcard position
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

    private int getCurrentNum()
    {
        return currentNum;
    }

    //array randomizer for button
    private void randomizeArrays(String[] word, String[] def, String[] pin)
    {
        Random rgen = new Random();

        for (int i = 0; i < word.length; i++)
        {
            //randomize words
            int randomPosition = rgen.nextInt(word.length);
            String temp = word[i];
            word[i] = word[randomPosition];
            word[randomPosition] = temp;

            //randomize defs
            String temp1 = def[i];
            def[i] = def[randomPosition];
            def[randomPosition] = temp1;

            //randomize pins
            String temp2 = pin[i];
            pin[i] = pin[randomPosition];
            pin[randomPosition] = temp2;
        }
    }

    public String getHashedChinese(String word)
    {
        String str = word;

        str = str.replaceAll("\\.", "")
                .replaceAll("（", "")
                .replaceAll("）", "")
                .replaceAll("，", "");

        String[] split = str.split("\\.");

        String split_str = split[0];

        char[] ch = split_str.toCharArray();
        String chars = "";

        for (char c : ch)
        {
            chars += (int) (c);
        }

        return chars;
    }

    //doesn't work
    @Override
    public void onBackPressed()
    {
        Intent i = new Intent();
        //i.putExtra("starred", starredNums);
        i.putExtra("starred", "wasdsdvcc");
        setResult(RESULT_OK, i);
        finish();
    }
}

