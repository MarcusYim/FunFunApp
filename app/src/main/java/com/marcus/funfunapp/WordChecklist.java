package com.marcus.funfunapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordChecklist extends AppCompatActivity
{
    ListView simpleListView;

    WordAdapter wordAdapter;

    //defining page to display on
    ListView simpleList;
    Button studyButton;

    Button[] selectButtons;
    Button allButton;

    //defining elements to be displayed

    List<String> allWordsList = new ArrayList<>();
    List<String> allEnglishList = new ArrayList<>();
    List<String> allPinList = new ArrayList<>();

    List<String>[] allWords;
    List<String>[] allEnglish;
    List<String>[] allPin;

    ArrayList<WordAdapter> adapters;

    int position;
    boolean[] isSelectAllArr;

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
        initAdapters();
        initCustomView();
    }

    private void initAdapters()
    {
        adapters = new ArrayList<>();
        boolean checkedBoxes[][] = new boolean[dialogueNames.length - 1][];


        for (int i = 0; i < dialogueNames.length - 1; i++)
        {
            WordAdapter wordAdapter = new WordAdapter(this, R.layout.item_view, allWords[i], allEnglish[i], allPin[i]);
            adapters.add(wordAdapter);
            checkedBoxes[i] = wordAdapter.getCheckedArray();
        }

        adapters.add(new WordAdapter(this, R.layout.item_view, allWordsList, allEnglishList, allPinList, checkedBoxes));

        position = adapters.size() - 1;
    }

    private void initCustomView()
    {
        //identify page to display on
        simpleList = (ListView) findViewById(R.id.simple_list_view);

        simpleList.setAdapter(adapters.get(adapters.size() - 1));

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
                intent.putExtra("end", endDialogue);
                intent.putExtra("start", startDialogue);
                startActivity(intent);
            }
        });

        isSelectAllArr = new boolean[endDialogue - startDialogue + 1];
        Arrays.fill(isSelectAllArr, true);

        allButton = findViewById(R.id.checklist_all_button);


        allButton.setText("Select All");
        allButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (isSelectAllArr[adapters.size() - 1])
                {
                    adapters.get(adapters.size() - 1).checkAll();
                    adapters.get(adapters.size() - 1).notifyDataSetChanged();
                    Arrays.fill(isSelectAllArr, false);
                    allButton.setText("Unselect All");
                }
                else if (!isSelectAllArr[adapters.size() - 1])
                {
                    adapters.get(adapters.size() - 1).uncheckAll();
                    adapters.get(adapters.size() - 1).notifyDataSetChanged();
                    Arrays.fill(isSelectAllArr, true);
                    allButton.setText("Select All");
                }
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

    private ArrayList<Integer> getChecked()
    {
        return wordAdapter.getChecked();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        String[] lessons = dialogueNames;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.drop_menu_view, dialogueNames);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(arrayAdapter);
        adapters.get(position).notifyDataSetChanged();
    }

    private void swapList(int position)
    {
        this.position = position - 1;

        if (position == 0)
        {
            simpleList = (ListView) findViewById(R.id.simple_list_view);
            simpleList.setAdapter(adapters.get(adapters.size() - 1));

            if (isSelectAllArr[adapters.size() - 1])
            {
                allButton.setText("Select All");
            }
            else if (!isSelectAllArr[adapters.size() - 1])
            {
                allButton.setText("Unselect All");
            }

            allButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if (isSelectAllArr[adapters.size() - 1])
                    {
                        adapters.get(adapters.size() - 1).checkAll();
                        adapters.get(adapters.size() - 1).notifyDataSetChanged();
                        Arrays.fill(isSelectAllArr, false);
                        allButton.setText("Unselect All");
                    }
                    else if (!isSelectAllArr[adapters.size() - 1])
                    {
                        adapters.get(adapters.size() - 1).uncheckAll();
                        adapters.get(adapters.size() - 1).notifyDataSetChanged();
                        Arrays.fill(isSelectAllArr, true);
                        allButton.setText("Select All");
                    }
                }
            });
        }
        else
        {
            simpleList = (ListView) findViewById(R.id.simple_list_view);
            simpleList.setAdapter(adapters.get(position - 1));

            if (isSelectAllArr[position - 1])
            {
                allButton.setText("Select All");
            }
            else if (!isSelectAllArr[position - 1])
            {
                allButton.setText("Unselect All");
            }

            allButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if (isSelectAllArr[position - 1])
                    {
                        adapters.get(position - 1).checkAll();
                        adapters.get(position - 1).notifyDataSetChanged();
                        isSelectAllArr[position - 1] = false;
                        allButton.setText("Unselect All");
                    }
                    else if (!isSelectAllArr[position - 1])
                    {
                        adapters.get(position - 1).uncheckAll();
                        adapters.get(position - 1).notifyDataSetChanged();
                        isSelectAllArr[position - 1] = true;
                        allButton.setText("Select All");
                    }
                }
            });
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
