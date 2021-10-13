package com.marcus.funfunapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;
import java.time.Instant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void login() throws Exception
    {
        String code = "1347bvweihj23tg8971hj12896sa";
        String password = "Marcusyim";
        String username = "janepylo@hotmail.com";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://www.api.funfunmandarin.com/fun-fun-mandarin/rsa/public_key?mobileCode=" + code).build();

        Response response = client.newCall(request).execute();


        ObjectMapper mapper = new ObjectMapper();

        InputStream fileInputStream = response.body().byteStream();

        Posty post = mapper.readValue(fileInputStream, Posty.class);
        fileInputStream.close();

        Taggy tag = post.getData();

        String modulusString = tag.getModulus();
        String exponentString = tag.getExponent();

        byte[] decodedMod = Base64.decodeBase64(modulusString);
        String modulusHex = Hex.encodeHexString(decodedMod);

        byte[] decodedExp = Base64.decodeBase64(exponentString);
        String exponentHex = Hex.encodeHexString(decodedExp);

        BigInteger modulus = new BigInteger(modulusHex, 16);
        BigInteger exponent = new BigInteger(exponentHex, 16);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey generatedPublic = kf.generatePublic(keySpec);

        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.ENCRYPT_MODE, generatedPublic);
        byte[] encryptedData = cipher1.doFinal(password.getBytes());

        String stringB64 = Base64.encodeBase64String(encryptedData);


        Algorithm algorithm = Algorithm.HMAC256(Base64.decodeBase64("RnVuRnVuX01hbmRhcmluX1Rva2VuQDIwMjAwMzI4="));

        Map<String, String> payload = new HashMap<>();
        payload.put("phoneNum", username);
        payload.put("sub", username + "," + Instant.now().getEpochSecond());

        String token = JWT.create()
                .withPayload(payload)
                .sign(algorithm);


        RequestBody formBody = new FormBody.Builder()
                .add("email", username)
                .add("rsaPassword", stringB64)
                .add("deviceType", "Android")
                .add("mobileCode", code)
                .add("loginType", "web")
                .build();

        Request request1 = new Request.Builder()
                .header("Authorization", token)
                .url("https://www.api.funfunmandarin.com/fun-fun-mandarin/pre_user/email_login")
                .post(formBody)
                .build();

        Call call = client.newCall(request1);
        Response response1 = call.execute();

        System.out.println(response1);

        Scanner sc = new Scanner(response1.body().byteStream());

        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }

    }
}

