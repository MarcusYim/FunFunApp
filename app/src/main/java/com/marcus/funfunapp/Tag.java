package com.marcus.funfunapp;

public class Tag
{
    private String modulus;
    private String exponent;

    public Tag()
    {

    }

    public Tag(String modulus, String exponent)
    {
        super();
        this.modulus = modulus;
        this.exponent = exponent;
    }

    public String getModulus()
    {
        return modulus;
    }

    public String getExponent()
    {
        return exponent;
    }
}
