package com.marcus.funfunapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
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
            public void onClick(View v)
            {
                //to be replaced
                //tempCheck(v);

                Toast.makeText(MainActivity.this, "You have Authenticated Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), LevelSelect.class);
                startActivity(intent);
            }
        });
    }

    //apparently you need permission from the api to use .equals
    @TargetApi(23)
    public void tempCheck(View v)
    {
        //checks credentials
        if (Objects.equals(username.getText().toString(), "admin") && Objects.equals(password.getText().toString(),"admin"))
        {
            Toast.makeText(MainActivity.this, "You have Authenticated Successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(v.getContext(), WordChecklist.class);
            startActivity(intent);
        }

        else
        {
            Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
        }
    }


}

