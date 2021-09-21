package com.marcus.funfunapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarHolder
{
    //final String[] levelImages = new String[] {"level_1_image.png", "level_2_image.png", "level_3_image.png", "level_4_image.png", "level_5_image.png", "level_6_image.png"};
    final String[] levelImages = new String[] {"level_1_image.png", "level_2_image.png", "level_3_image.png", "level_4_image.png"};
    final String[] levelNames = new String[] {"LEVEL 1", "LEVEL 2", "LEVEL 3", "LEVEL 4"};
    final String[] DialogueNames = new String[] {"Dialogue 1", "Dialogue 2", "Dialogue 3", "Dialogue 4", "Dialogue 5", "Dialogue 6", "Dialogue 7", "Dialogue 8", "Dialogue 9", "Dialogue 10", "Dialogue 11", "Dialogue 12", "Dialogue 13", "Dialogue 14", "Dialogue 15", "Dialogue 16", "Dialogue 17", "Dialogue 18", "Dialogue 19", "Dialogue 20", "Dialogue 21", "Dialogue 22", "Dialogue 23", "Dialogue 24", "Dialogue 25", "Dialogue 26", "Dialogue 27", "Dialogue 28", "Dialogue 29", "Dialogue 30", "Dialogue 31", "Dialogue 32", "Dialogue 33"};
    int[] dialoguesPerLevel = new int[] {8, 8, 9, 8};
    private int[] previousDialogueSums;


    public VarHolder(boolean calculatePreviousSums)
    {
        if (calculatePreviousSums)
        {
            previousDialogueSums = new int[dialoguesPerLevel.length];

            int count = 0;
            for (int i = 0; i < dialoguesPerLevel.length; i++)
            {
                previousDialogueSums[i] = count;
                count += dialoguesPerLevel[i];
            }
        }
    }

    public VarHolder()
    {
        //too lazy to change all of the class calls
    }

    public String[] getLevelImages()
    {
        return levelImages;
    }

    public String[] getLevelNames()
    {
        return levelNames;
    }

    public int getNumImages()
    {
        return levelImages.length;
    }

    public int getDialoguesPerLevel(int index)
    {
        return dialoguesPerLevel[index];
    }

    public int getNumPreviousDialogues(int index)
    {
        return previousDialogueSums[index];
    }

    public String[] getSubDialogueArray(int start, int end)
    {
        String[] ret = new String[end - start + 1];

        ret[0] = "All";

        int count = 1;

        for (int i = start; i < end; i++)
        {
            ret[count] = DialogueNames[i];
            count++;
        }

        return ret;
    }
}
