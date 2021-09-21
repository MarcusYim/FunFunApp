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
    boolean[] starHolder;
    boolean[][] checkedBoxes;
    boolean[][] starBoxes;

    int curr;
    int count = 0;
    int leftover = 0;

    int currStar;
    int countStar = 0;
    int leftoverStar = 0;

    int previousDialogueNum;

    public WordAdapter(Context context, int textViewResourceId, List<String> wordObjects, List<String> defObjects, List<String> pinObjects, int numObject)
    {
        super(context, textViewResourceId, wordObjects);

        wordList = wordObjects;
        defList = defObjects;
        pinList = pinObjects;

        previousDialogueNum = numObject;

        createCheckedHolder();
    }

    public WordAdapter(Context context, int textViewResourceId, List<String> wordObjects, List<String> defObjects, List<String> pinObjects, boolean[][] checkedObjects, boolean[][] starObjects)
    {
        super(context, textViewResourceId, wordObjects);

        wordList = wordObjects;
        defList = defObjects;
        pinList = pinObjects;
        checkedBoxes = checkedObjects;
        starBoxes = starObjects;

        createCheckedHolder();
    }

    @Override
    public int getCount()
    {
        return wordList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        /*
        if (position >= wordList.size())
        {
            View v = convertView;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_view, null);
            return v;
        }

         */

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

        CheckBox starBox = (CheckBox) v.findViewById(R.id.star_box);

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

        currStar = position;
        countStar = 0;
        leftoverStar = 0;

        if (starBoxes == null)
        {
            starBox.setChecked(starHolder[position]);
        }
        else
        {
            int subtractorStar = starBoxes[0].length;

            while (currStar - subtractorStar >= 0)
            {
                currStar -= subtractorStar;
                countStar++;
                subtractorStar = starBoxes[countStar].length;
            }

            leftoverStar = currStar;

            starBox.setChecked(starBoxes[countStar][leftoverStar]);
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

        starBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (starBoxes == null)
                {
                    starHolder[position] = isChecked;
                }
                else
                {
                    currStar = position;
                    countStar = 0;
                    leftoverStar = 0;

                    int subtractorStar = starBoxes[0].length;

                    while (currStar - subtractorStar >= 0)
                    {
                        currStar -= subtractorStar;
                        countStar++;
                        subtractorStar = starBoxes[countStar].length;
                    }

                    leftoverStar = currStar;

                    starBoxes[countStar][leftoverStar] = isChecked;
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

        starHolder = new boolean[getCount()];

        for (int i = 0; i < getCount(); i++)
        {
            starHolder[i] = false;
        }
    }

    public boolean[] getCheckedArray()
    {
        return checkedHolder;
    }

    public boolean[] getStarArray()
    {
        return starHolder;
    }

    public ArrayList<Integer> getChecked()
    {
        ArrayList<Integer> ret = new ArrayList<Integer>();

        if (checkedBoxes == null)
        {
            for (int i = 0; i < checkedHolder.length; i++)
            {
                if (checkedHolder[i])
                {
                    ret.add(previousDialogueNum + i);
                }
            }
        }
        else
        {
            int count = 0;
            for (int i = 0; i < checkedBoxes.length; i++)
            {
                for (int x = 0; x < checkedBoxes[i].length; x++)
                {
                    if (checkedBoxes[i][x])
                    {
                        ret.add(count);
                    }

                    count++;
                }
            }
        }

        return ret;
    }

    public ArrayList<Integer> getStarChecked()
    {
        ArrayList<Integer> ret = new ArrayList<Integer>();

        if (starBoxes == null)
        {
            for (int i = 0; i < starHolder.length; i++)
            {
                if (starHolder[i])
                {
                    ret.add(previousDialogueNum + i);
                }
            }
        }
        else
        {
            int countStar = 0;
            for (int i = 0; i < starBoxes.length; i++)
            {
                for (int x = 0; x < starBoxes[i].length; x++)
                {
                    if (starBoxes[i][x])
                    {
                        ret.add(countStar);
                    }

                    countStar++;
                }

                countStar--;
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
                Arrays.fill(checkedBoxes[i], true);
            }
        }
    }

    public void checkAllStar()
    {
        if (starBoxes == null)
        {
            Arrays.fill(starHolder, true);
        }
        else
        {
            for (int i = 0; i < starBoxes.length; i++)
            {
                Arrays.fill(starBoxes[i], true);
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

    public void uncheckAllStar()
    {
        if (starBoxes == null)
        {
            Arrays.fill(starHolder, true);
        }
        else
        {
            for (int i = 0; i < starBoxes.length; i++)
            {
                Arrays.fill(starBoxes[i], false);
            }
        }
    }
}
