package com.example.caelum.agendaalunos.client.aluno;

import android.os.StrictMode;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by android5843 on 10/12/15.
 */
public class AlunoClient {

    private static final String URL = "http://www.caelum.com.br/mobile";

    public String send(String json) {
        HttpPost postCall = new HttpPost(URL);

        try {
            postCall.setEntity(new StringEntity(json));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        postCall.setHeader("Content-Type", "application/json");
        postCall.setHeader("Accept", "application/json");

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            response = client.execute(postCall);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String bodyContent = null;

        try {
            bodyContent = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bodyContent;
    }

}
