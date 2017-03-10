package com.example.sun0002.ownview.presenter;

import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.sun0002.ownview.entity.DianZhi;
import com.example.sun0002.ownview.entity.Result;
import com.example.sun0002.ownview.model.NetClient;
import com.example.sun0002.ownview.ui.MainActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sun0002 on 2017/3/8.
 */

public class ShiftPresenter {
    public static final String TAG = ShiftPresenter.class.getName();
    private ShiftInterface shiftInterfacelistner;
    public void setShiftInterfacelistner(ShiftInterface shiftInterfacelistner){
        this.shiftInterfacelistner = shiftInterfacelistner;
    }

    public ShiftPresenter(ShiftInterface shiftInterfacelistner) {
        this.shiftInterfacelistner = shiftInterfacelistner;
    }
    public void query(String num){
        NetClient.getInstance().getUserApi().login(num).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

//                List<Result.BBean> b = response.body().getB();
//                Log.e(TAG, "onResponse: " + b.size());

                Result result = response.body();
                if (result.getA().equals("0")){
                    shiftInterfacelistner.succsess(result);
                }else {
                    Log.e(TAG, "onResponse: " + "请求有误");
                    shiftInterfacelistner.error();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
    public void queryDianZhi(String str){
        Log.d(TAG, "queryDianZhi() called with: str = [" + str + "]");
        NetClient.getInstance().getUserApi().dianZhi(str).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                JSONObject jsonObject = null;
                try {
                    jsonObject = JSONObject.parseObject(response.body().string());
                    if (jsonObject !=null){
                        shiftInterfacelistner.succsessRobot(jsonObject);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void queryDianZhi2(String str){
        NetClient.getInstance().getUserApi().dianZhi(str).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                JSONObject jsonObject = null;
                try {
                    jsonObject = JSONObject.parseObject(response.body().string());
                    if (jsonObject !=null){
                        shiftInterfacelistner.succsessRBed(jsonObject);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void quaryDianzhiSTRollingBed(String string){
        NetClient.getInstance().getUserApi().dianZhi(string).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = JSON.parseObject(response.body().string());
                    if (jsonObject != null){
                        if (jsonObject.getString("a").equals("0")){
                            shiftInterfacelistner.succsessSTBed(jsonObject);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void quaryDianZhiOfST(final String str, final View view, final Result.BBean  bBean){
        NetClient.getInstance().getUserApi().dianZhi(str).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = JSON.parseObject(response.body().string());
                    if (jsonObject!=null){
                        shiftInterfacelistner.succsessSTBedInit(jsonObject,str,view,bBean);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void quaryDianzhiSTRollingBedTop(String string){
        NetClient.getInstance().getUserApi().dianZhi(string).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = JSON.parseObject(response.body().string());
                    if (jsonObject!=null){
                        shiftInterfacelistner.succsessSTbedTop(jsonObject);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
