package com.example.sun0002.ownview.presenter;

import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.example.sun0002.ownview.entity.DianZhi;
import com.example.sun0002.ownview.entity.Result;

/**
 * Created by sun0002 on 2017/3/8.
 */

public interface ShiftInterface {
    void succsess(Result result);
    void error();
    void succsessRobot(JSONObject jsonObject);
    void succsessRBed(JSONObject jsonObject);
    void succsessSTBed(JSONObject jsonObject);
    void succsessSTBedInit(JSONObject jsonObject, String str, View view, Result.BBean  bBean);
    void succsessSTbedTop(JSONObject jsonObject);

}

