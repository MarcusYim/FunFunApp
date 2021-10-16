package com.marcus.funfunapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class AuthPost
{
    private AuthTag data;

    public AuthPost()
    {

    }

    public AuthPost(AuthTag data)
    {
        this.data = data;
    }

    public AuthTag getData()
    {
        return data;
    }
}