package com.lx.criminallent;

import com.squareup.*;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BaseHttpClient {
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    // 01. 定义okhttp
    private  final  OkHttpClient client  = new OkHttpClient();

    public BaseHttpClient(){

        //client.connectTimeoutMillis();
    }


    /**
     * 发送一个表单请求
     * @throws Exception
     */
    public void SendForm() throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }

    /**POST 请求
     * 发送一个string请求
     * @throws Exception
     */
    public void SendPostString() throws Exception {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }

    /**POST 请求
     * 发送一个From请求
     * @throws Exception
     */
    public   void SendPostFrom() throws Exception {

        RequestBody body = new FormBody.Builder()
                .add("name", "sy")//添加参数体
                .add("age", "18")
                .build();

        Request request = new Request.Builder()
                .post(body) //请求参数
                .url("http://123.207.70.54:8080/SpringMvc/hello")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
    }

    /**Get请求
     * 发送一个From请求
     * @throws Exception
     */
    public void SendGetFrom() throws Exception {

        Request request = new Request.Builder()
                .get() //请求参数
                .url("http://123.207.70.54:8080/SpringMvc/hello")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
    }
}
