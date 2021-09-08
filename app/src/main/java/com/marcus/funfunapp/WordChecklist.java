package com.marcus.funfunapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

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

    List<String> allWordsList = new ArrayList<>();
    List<String> allEnglishList = new ArrayList<>();
    List<String> allPinList = new ArrayList<>();

    List<String>[] allWords;
    List<String>[] allEnglish;
    List<String>[] allPin;

    int startDialogue;
    int endDialogue;
    String[] dialogueNames;

    AutoCompleteTextView autoCompleteTextView;

    String[] wordCsv;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_checklist);

        Bundle extras = getIntent().getExtras();
        startDialogue = extras.getInt("start");
        endDialogue = extras.getInt("end");
        dialogueNames = extras.getStringArray("dialogues");

        parseCsv();
        combineAllWords();
        initDropdownMenu();
        initCustomView();
    }

    private void initCustomView()
    {
        VarHolder var = new VarHolder();
        pinArr = var.getPins();

        pinList.addAll(Arrays.asList(pinArr));
        //identify page to display on
        simpleList = (ListView) findViewById(R.id.simple_list_view);

        //create and set custom adapter
        wordsAdapter = new WordsAdapter(this, R.layout.item_view, allWordsList, allEnglishList, allPinList);
        simpleList.setAdapter(wordsAdapter);

        studyButton = findViewById(R.id.study_button);

        studyButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Toast.makeText(WordChecklist.this, "" + cool, Toast.LENGTH_SHORT).show();

                //Toast.makeText(WordChecklist.this, allEnglish[0].get(2), Toast.LENGTH_SHORT).show();

                ArrayList<Integer> temp = getChecked();
                Intent intent = new Intent(v.getContext(), FlashCard.class);
                intent.putExtra("checked", temp);
                startActivity(intent);
            }
        });
    }

    private void initDropdownMenu()
    {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.drop_menu_view, dialogueNames);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                swapList(arg2);
            }
        });
    }

    private void parseCsv()
    {
        allWords = new List[endDialogue - startDialogue];

        int count = 0;
        for (int i = startDialogue; i < endDialogue; i++)
        {
            try
            {
                String uri = "@raw/dialogue_" + i + "_chinese";
                int csvResource = getResources().getIdentifier(uri, null, getPackageName());
                CSVReader reader = new CSVReader(new InputStreamReader(getResources().openRawResource(csvResource)));
                allWords[count] = Arrays.asList(reader.readNext());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(this, "Error in file reading", Toast.LENGTH_SHORT).show();
            }

            count++;
        }

        allEnglish = new List[endDialogue - startDialogue];

        count = 0;
        for (int i = startDialogue; i < endDialogue; i++)
        {
            try
            {
                String uri = "@raw/dialogue_" + i + "_english";
                int csvResource = getResources().getIdentifier(uri, null, getPackageName());
                CSVReader reader = new CSVReader(new InputStreamReader(getResources().openRawResource(csvResource)));
                allEnglish[count] = Arrays.asList(reader.readNext());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(this, "Error in file reading", Toast.LENGTH_SHORT).show();
            }

            count++;
        }

        allPin = new List[endDialogue - startDialogue];

        count = 0;
        for (int i = startDialogue; i < endDialogue; i++)
        {
            try
            {
                String uri = "@raw/dialogue_" + i + "_pin";
                int csvResource = getResources().getIdentifier(uri, null, getPackageName());
                CSVReader reader = new CSVReader(new InputStreamReader(getResources().openRawResource(csvResource)));
                allPin[count] = Arrays.asList(reader.readNext());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(this, "Error in file reading", Toast.LENGTH_SHORT).show();
            }

            count++;
        }
    }

    private ArrayList<Integer> getChecked()
    {
        return wordsAdapter.getChecked();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        String[] lessons = dialogueNames;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.drop_menu_view, dialogueNames);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    private void swapList(int position)
    {
        if (position == 0)
        {
            simpleList = (ListView) findViewById(R.id.simple_list_view);
            wordsAdapter = new WordsAdapter(this, R.layout.item_view, allWordsList, allEnglishList, allPinList);
            simpleList.setAdapter(wordsAdapter);
        }
        else
        {
            simpleList = (ListView) findViewById(R.id.simple_list_view);
            wordsAdapter = new WordsAdapter(this, R.layout.item_view, allWords[position - 1], allEnglish[position - 1], allPin[position - 1]);
            simpleList.setAdapter(wordsAdapter);
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
}
