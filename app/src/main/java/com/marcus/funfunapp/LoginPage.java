package com.marcus.funfunapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.Executor;


public class LoginPage extends AppCompatActivity
{
    Button loginButton;
    EditText username,password;

    protected void onCreate(Bundle savedInstanceState)
    {
        //sets the main screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //identifies these variables by their id
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v)
            {
                try {
                    login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String getRandomString(int len)
    {
        Random r = new Random();
        String ret = "";

        for (int i = 0; i < len / 2; i++)
        {
            ret += (char) (r.nextInt(26) + 'a');
            ret += (char) (r.nextInt(10) + '0');
        }

        return ret;
    }

    public void login()
    {
        Executor executor = new NetworkExecutor();
        executor.execute(new VerifyInfo());
    }
}

