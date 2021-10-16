package com.marcus.funfunapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class LevelPost
{
    private LevelTag[] data;

    public LevelPost()
    {

    }

    public LevelPost(LevelTag[] data)
    {
        this.data = data;
    }

    public LevelTag[] getData()
    {
        return data;
    }
}
