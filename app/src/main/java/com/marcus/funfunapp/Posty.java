package com.marcus.funfunapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Posty
{
    private Taggy data;

    public Posty()
    {

    }

    public Posty(Taggy data)
    {
        this.data = data;
    }

    public Taggy getData()
    {
        return data;
    }
}