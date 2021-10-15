package com.marcus.funfunapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Post
{
    private Tag data;

    public Post()
    {

    }

    public Post(Tag data)
    {
        this.data = data;
    }

    public Tag getData()
    {
        return data;
    }
}