package com.marcus.funfunapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarHolder
{
    final String[] words = new String[] {"你", "我", "是", "那", "他", "我们"};
    final String[] defs = new String[] {"you", "me", "is", "that", "him", "we"};
    final String[] pins = new String[] {"ni", "wo", "shi", "na", "ta", "wo men"};
    //final String[] levelImages = new String[] {"level_1_image.png", "level_2_image.png", "level_3_image.png", "level_4_image.png", "level_5_image.png", "level_6_image.png"};
    final String[] levelImages = new String[] {"level_1_image.png", "level_2_image.png", "level_3_image.png"};
    final String[] levelNames = new String[] {"LEVEL 1", "LEVEL 2", "LEVEL 3"};
    final String[] DialogueNames = new String[] {"Dialogue 1", "Dialogue 2", "Dialogue 3", "Dialogue 4", "Dialogue 5", "Dialogue 6", "Dialogue 7", "Dialogue 8", "Dialogue 9", "Dialogue 10"};
    int[] dialoguesPerLevel = new int[] {3, 3, 4};
    final String[][] allWords = new String[][] {{"test1", "test2"}, {"test3", "test4"}, {"test5", "test6"}, {"test7", "test8"}, {"test9", "test10"}, {"test11", "test12"}, {"test13", "test14"}, {"test15", "test16"}, {"test17", "test18"}, {"test19", "test20"}};
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

    public String[] getWords()
    {
        return words;
    }

    public String[] getDefs()
    {
        return defs;
    }

    public String[] getPins()
    {
        return pins;
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

    public String[] getWordsByLesson(int index)
    {
        return allWords[index];
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

    public String[][] getAllWords()
    {
        return allWords;
    }
}
