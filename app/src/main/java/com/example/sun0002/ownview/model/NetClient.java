package com.example.sun0002.ownview.model;

import com.example.sun0002.ownview.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sun0002 on 2017/3/8.
 */

public class NetClient {
    public static NetClient netClient;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private UserApi userApi;


    public static NetClient getInstance(){
        if (netClient == null){
            netClient = new NetClient();
        }
        return netClient;
    }
    private NetClient(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)//日志拦截器
                .build();
//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                .create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://60.28.240.35:6300/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public UserApi getUserApi(){

        if (userApi == null){
            userApi = retrofit.create(UserApi.class);
        }
        return userApi;
    }


    /**
     * //设置发送的请求
     *
     * @return Call对象，用于处理返回的响应
     */
    public okhttp3.Call register(){

//        User user = new User(diagrame_name);
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("diagrame_name","22");
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url("http://60.28.240.35:6300/padApi/Diagrame/DiagramItemsForLoop")
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }
}
