package com.marcus.funfunapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class LoginPage extends AppCompatActivity
{
    Button loginButton;
    EditText username,password;

    protected void onCreate(Bundle savedInstanceState)
    {
        //sets the main screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

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

    public void login() throws ExecutionException, InterruptedException
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        String code = getRandomString(10);

        Future<String[]> result = executor.submit(new VerifyInfo(code, username.getText().toString(), password.getText().toString()));

        String[] output = result.get();

        if (output == null)
        {
            Toast.makeText(LoginPage.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(LoginPage.this, "You have Authenticated Successfully", Toast.LENGTH_SHORT).show();
            Future<Long[]> result1 = executor.submit(new GetPurchased(code, output[0], output[1]));

            Long[] output1 = result1.get();
            Intent intent = new Intent(LoginPage.this, LevelSelect.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("purchased", output1);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
}

