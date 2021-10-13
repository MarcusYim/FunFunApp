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



import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordChecklist extends AppCompatActivity
{
    //defining page to display on
    ListView simpleList;
    Button studyButton;

    Button allButton, starButton;

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

    boolean[][] starredArr;

    AutoCompleteTextView autoCompleteTextView;

    final int FLASHCARD_CODE = 0;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_checklist);

        Bundle extras = getIntent().getExtras();
        startDialogue = extras.getInt("start");
        endDialogue = extras.getInt("end");
        dialogueNames = extras.getStringArray("dialogues");

        VarHolder varHolder = new VarHolder();
        starredArr = varHolder.getSubStarredArray(endDialogue, startDialogue);

        parseCsv();
        combineAllWords();
        initDropdownMenu();
        initAdapters();
        initCustomView();
    }

    private void initAdapters()
    {
        boolean checkedBoxes[][] = new boolean[dialogueNames.length - 1][];

        adapters = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < dialogueNames.length - 1; i++)
        {
            WordAdapter wordAdapter = new WordAdapter(this, R.layout.item_view, allWords[i], allEnglish[i], allPin[i], count, starredArr[i]);
            adapters.add(wordAdapter);
            checkedBoxes[i] = wordAdapter.getCheckedArray();
            count += allWords[i].size();
        }

        adapters.add(new WordAdapter(this, R.layout.item_view, allWordsList, allEnglishList, allPinList, checkedBoxes, starredArr));

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
                ArrayList<Integer> star = getStarChecked();

                if (temp.size() > 0)
                {
                    Intent intent = new Intent(v.getContext(), FlashCard.class);
                    intent.putExtra("checked", temp);
                    intent.putExtra("end", endDialogue);
                    intent.putExtra("start", startDialogue);
                    intent.putExtra("starred", star);
                    startActivity(intent);
                }
            }
        });

        starButton = findViewById(R.id.checklist_star_button);

        starButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ArrayList<Integer> temp = getStarChecked();

                if (temp.size() > 0)
                {
                    Intent intent = new Intent(v.getContext(), FlashCard.class);
                    intent.putExtra("checked", temp);
                    intent.putExtra("end", endDialogue);
                    intent.putExtra("start", startDialogue);
                    intent.putExtra("starred", temp);
                    startActivityForResult(intent, FLASHCARD_CODE);
                }
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
        VarHolder varHolder = new VarHolder();

        allWords = varHolder.getSubChineseArray(endDialogue, startDialogue);
        allEnglish = varHolder.getSubEnglishArray(endDialogue, startDialogue);
        allPin = varHolder.getSubPinArray(endDialogue, startDialogue);
    }

    private ArrayList<Integer> getChecked()
    {
        return adapters.get(position).getChecked();
    }

    private ArrayList<Integer> getStarChecked()
    {
        return adapters.get(position).getStarChecked();
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
        if (position == 0)
        {
            this.position = adapters.size() - 1;
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

            ArrayList<Integer> bool = adapters.get(adapters.size() - 1).getStarChecked();
        }
        else
        {
            this.position = position - 1;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("TAG", "np");

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FLASHCARD_CODE && resultCode == RESULT_OK)
        {
            String newId = data.getStringExtra("starred");
            Log.d("TAG", newId);
        }

        Log.d("TAG", "np");
    }
}
