package com.marcus.funfunapp;

public class AuthTag
{
    private String mobileCode;
    private String token;
    private String random;
    private String loginType;

    public AuthTag()
    {

    }

    public AuthTag(String mobileCode, String token, String random, String loginType)
    {
        super();
        this.mobileCode = mobileCode;
        this.token = token;
        this.random = random;
        this.loginType = loginType;
    }

    public String getMobileCode()
    {
        return mobileCode;
    }

    public String getToken()
    {
        return token;
    }

    public String getRandom()
    {
        return random;
    }

    public String getLoginType()
    {
        return loginType;
    }
}
