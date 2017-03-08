package com.example.sun0002.ownview.model;

import com.example.sun0002.ownview.entity.DianZhi;
import com.example.sun0002.ownview.entity.Result;
import com.example.sun0002.ownview.entity.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sun0002 on 2017/3/8.
 */

public interface UserApi {

    @FormUrlEncoded
    @POST("padApi/Diagrame/DiagramItemsForLoop")
    Call<Result> login(@Field("diagrame_name") String username);
//    ZQAPI/pointValue/DHPointValues2

    @FormUrlEncoded
    @POST("ZQAPI/pointValue/DHPointValues2")
    Call<ResponseBody> dianZhi(@Field("point_names") String diagrame_name);
}
