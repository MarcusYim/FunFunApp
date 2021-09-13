package com.marcus.funfunapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordAdapter extends ArrayAdapter<String>
{
    List<String> wordList = new ArrayList<>();
    List<String> defList = new ArrayList<>();
    List<String> pinList = new ArrayList<>();
    boolean[] checkedHolder;
    boolean[][] checkedBoxes;

    int curr;
    int count = 0;
    int leftover = 0;

    public WordAdapter(Context context, int textViewResourceId, List<String> wordObjects, List<String> defObjects, List<String> pinObjects)
    {
        super(context, textViewResourceId, wordObjects);

        wordList = wordObjects;
        defList = defObjects;
        pinList = pinObjects;

        createCheckedHolder();
    }

    public WordAdapter(Context context, int textViewResourceId, List<String> wordObjects, List<String> defObjects, List<String> pinObjects, boolean[][] checkedObjects)
    {
        super(context, textViewResourceId, wordObjects);

        wordList = wordObjects;
        defList = defObjects;
        pinList = pinObjects;
        checkedBoxes = checkedObjects;

        createCheckedHolder();
    }

    @Override
    public int getCount()
    {
        return wordList.size() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (position >= wordList.size())
        {
            View v = convertView;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_view, null);
            return v;
        }

        //create inflater
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_view, null);

        //set word and def in their respective textViews
        TextView wordView = (TextView) v.findViewById(R.id.wordView);
        wordView.setText(wordList.get(position));

        TextView defView = (TextView) v.findViewById(R.id.defView);
        defView.setText(defList.get(position));

        TextView pinView = (TextView) v.findViewById(R.id.pinView);
        pinView.setText(pinList.get(position));

        CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);

        curr = position;
        count = 0;
        leftover = 0;

        if (checkedBoxes == null)
        {
            checkBox.setChecked(checkedHolder[position]);
        }
        else
        {
            int subtractor = checkedBoxes[0].length;

            while (curr - subtractor >= 0)
            {
                curr -= subtractor;
                count++;
                subtractor = checkedBoxes[count].length;
            }

            leftover = curr;

            checkBox.setChecked(checkedBoxes[count][leftover]);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (checkedBoxes == null)
                {
                    checkedHolder[position] = isChecked;
                }
                else
                {
                    curr = position;
                    count = 0;
                    leftover = 0;

                    int subtractor = checkedBoxes[0].length;

                    while (curr - subtractor >= 0)
                    {
                        curr -= subtractor;
                        count++;
                        subtractor = checkedBoxes[count].length;
                    }

                    leftover = curr;

                    checkedBoxes[count][leftover] = isChecked;
                }
            }
        });

        return v;
    }

    private void createCheckedHolder()
    {
        checkedHolder = new boolean[getCount()];

        for (int i = 0; i < getCount(); i++)
        {
            checkedHolder[i] = false;
        }
    }

    public boolean[] getCheckedArray()
    {
        return checkedHolder;
    }

    public ArrayList<Integer> getChecked()
    {
        ArrayList<Integer> ret = new ArrayList<Integer>();

        for (int i = 0; i < checkedHolder.length; i++)
        {
            if (checkedHolder[i])
            {
                ret.add(i);
            }
        }

        return ret;
    }

    public void checkAll()
    {
        if (checkedBoxes == null)
        {
            Arrays.fill(checkedHolder, true);
        }
        else
        {
            for (int i = 0; i < checkedBoxes.length; i++)
            {
                Log.d("TAG", "" + i + " " + checkedBoxes[i]);
                Arrays.fill(checkedBoxes[i], true);
            }
        }
    }

    public void uncheckAll()
    {
        if (checkedBoxes == null)
        {
            Arrays.fill(checkedHolder, false);
        }
        else
        {
            for (int i = 0; i < checkedBoxes.length; i++)
            {
                Log.d("TAG", "" + i + " " + checkedBoxes[i]);
                Arrays.fill(checkedBoxes[i], false);
            }
        }
    }
}
