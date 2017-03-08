package com.example.sun0002.ownview.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.sun0002.ownview.entity.DianZhi;
import com.example.sun0002.ownview.entity.Result;

/**
 * Created by sun0002 on 2017/3/8.
 */

public interface ShiftInterface {
    void succsess(Result result);
    void error();
    void succsess1(JSONObject jsonObject);
}
