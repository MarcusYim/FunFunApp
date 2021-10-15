package com.marcus.funfunapp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;

public class VerifyInfo implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            String code = "1347bvweihj23tg8971hj12896sa";
            String password = "Marcusyim";
            String username = "janepylo@hotmail.com";

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url("https://www.api.funfunmandarin.com/fun-fun-mandarin/rsa/public_key?mobileCode=" + code).build();

            Response response = client.newCall(request).execute();

            ObjectMapper mapper = new ObjectMapper();

            InputStream fileInputStream = response.body().byteStream();

            Post post = mapper.readValue(fileInputStream, Post.class);
            fileInputStream.close();

            Tag tag = post.getData();

            String modulusString = tag.getModulus();
            String exponentString = tag.getExponent();


            /*
            String modulusString = "AIhDb/eTZx0a2a1nzAu9oX1jLp6CBecRF35EdswcQja021eKihLyrxlEpSLFYNGqDRpCqrQF/rKdkxR8Va0f61nDMBfb8k0LDPV9qx6ihkpQ1bN0BO/QQ8xavMSjG2eikfygUqS28goTByBk464ezirqPWET3m7Y/tKGDBbPzw0b";
            String exponentString = "AQAB";

             */

            byte[] decodedMod = Base64.decodeBase64(modulusString);
            String modulusHex = Hex.encodeHexString(decodedMod);

            byte[] decodedExp = Base64.decodeBase64(exponentString);
            String exponentHex = Hex.encodeHexString(decodedExp);

            BigInteger modulus = new BigInteger(modulusHex, 16);
            BigInteger exponent = new BigInteger(exponentHex, 16);

            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey generatedPublic = kf.generatePublic(keySpec);

            Cipher cipher1 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher1.init(Cipher.ENCRYPT_MODE, generatedPublic);
            byte[] encryptedData = cipher1.doFinal(password.getBytes());

            String stringB64 = Base64.encodeBase64String(encryptedData);

            Algorithm algorithm = Algorithm.HMAC256(Base64.decodeBase64("RnVuRnVuX01hbmRhcmluX1Rva2VuQDIwMjAwMzI4="));

            Map<String, String> payload = new HashMap<String, String>();
            payload.put("phoneNum", username);
            payload.put("sub", username + "," + (123456789));

            //System.currentTimeMillis() / 1000L

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

            Scanner sc = new Scanner(response1.body().byteStream());

            while (sc.hasNextLine())
            {
                System.out.println(sc.nextLine());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
