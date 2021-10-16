package com.marcus.funfunapp;

import android.util.Log;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;

import javax.crypto.Cipher;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetPurchased implements Callable
{
    String code;
    String token;
    String random;

    public GetPurchased(String code, String token, String random)
    {
        this.code = code;
        this.token = token;
        this.random = random;
    }

    @Override
    public Long[] call() throws Exception
    {
        try
        {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://www.api.funfunmandarin.com/fun-fun-mandarin/user/level/get_list")
                    .addHeader("funfunmandarin", "{\"mobileCode\":\"" + code + "\",\"token\":\"" + token + "\",\"random\":\"" + random + "\",\"loginType\":\"mobile\"}")
                    .build();



            Response response = client.newCall(request).execute();

            ObjectMapper mapper = new ObjectMapper();

            InputStream fileInputStream = response.body().byteStream();

            LevelPost post = mapper.readValue(fileInputStream, LevelPost.class);
            fileInputStream.close();

            LevelTag[] tags = post.getData();

            Long[] ret = new Long[tags.length];

            for (int i = 0; i < tags.length; i++)
            {
                ret[i] = tags[i].getValidDate();
            }

            return ret;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
