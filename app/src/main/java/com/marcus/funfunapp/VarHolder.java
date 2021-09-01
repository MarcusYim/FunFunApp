package com.marcus.funfunapp;

import java.util.ArrayList;

public class VarHolder
{
    final String[] words = new String[] {"你", "我", "是", "那", "他", "我们"};
    final String[] defs = new String[] {"you", "me", "is", "that", "him", "we"};
    final String[] pins = new String[] {"ni", "wo", "shi", "na", "ta", "wo men"};
    final String[] levelImages = new String[] {"level_1_image.png", "level_2_image.png", "level_3_image.png", "level_4_image.png", "level_5_image.png", "level_6_image.png"};
    final String[] levelNames = new String[] {"level 1", "level 2", "level 3", "level 4", "level 5", "level 6"};

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
}
