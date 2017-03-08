package com.example.sun0002.ownview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.sun0002.ownview.R;
import com.example.sun0002.ownview.entity.Result;
import com.example.sun0002.ownview.model.NetClient;
import com.example.sun0002.ownview.presenter.ShiftInterface;
import com.example.sun0002.ownview.presenter.ShiftPresenter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ShiftInterface {
    public static final String TAG = MainActivity.class.getName();
    @BindView(R.id.button)
    Button button;

    private StringBuilder stringBuilder = new StringBuilder();
    //    @BindView(R.id.shift)
//    ShiftView shift;
    @BindView(R.id.al)
    AbsoluteLayout al;
    private List<Result.BBean> beanList;
    private ShiftPresenter presenter;
    private List<String> stringList;
    private View view;
    private Map<View, Result.BBean> viewBBeanMap;
    private List<Map<View, Result.BBean>> mapList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new ShiftPresenter(this);
        String num = "22";
        presenter.query(num);

//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        LinearLayout.LayoutParams layoutParams1=new LinearLayout.LayoutParams(60,60);
//        view.setLayoutParams(layoutParams1);
//        layoutParams.width = 120;
//        layoutParams.height = 160;
//        view.setLayoutParams(layoutParams);

        initLayout();


//        okHttpUtilsRequast();
//
//        okhttpRequast();
//        LayoutInflater.from(this).i
    }

    private void initLayout() {

//
    }

    private void okhttpRequast() {
        NetClient.getInstance().register().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                Log.e(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    private void okHttpUtilsRequast() {
        OkHttpUtils.post().addParams("diagrame_name", "22").url("http://60.28.240.35:6300/padApi/Diagrame/DiagramItemsForLoop").build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("response", response);
            }
        });
    }

    private void retrofitRequeast() {
        NetClient.getInstance().getUserApi().login("22").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                List<Result.BBean> b = response.body().getB();
                Log.e(TAG, "onResponse: " + b.size());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    @Override
    public void succsess(Result result) {
        if (result == null) {
            return;
        } else {
            beanList = result.getB();
        }
        viewBBeanMap = new HashMap<>();
        mapList = new ArrayList<>();
        int i = 0;//判断逗号
        for (Result.BBean bBean : beanList) {
            if ("GlassRoom".equals(bBean.getItemType())) {
                if (bBean.getStrBorderStyle() == 0) {//实线
                    Log.e(TAG, "succsess: " + "bean:" + bBean);
                    view = LayoutInflater.from(this).inflate(R.layout.layout_shixian, null);
                    AbsoluteLayout.LayoutParams layoutParams4 = new AbsoluteLayout.LayoutParams((int) bBean.getW(), (int) bBean.getH(),
                            (int) bBean.getX(), (int) bBean.getY());
                    al.addView(view, layoutParams4);
                } else {//虚线
                    view = LayoutInflater.from(this).inflate(R.layout.layout_xuxian, null);
                    AbsoluteLayout.LayoutParams layoutParams3 = new AbsoluteLayout.LayoutParams((int) bBean.getW(), (int) bBean.getH(),
                            (int) bBean.getX(), (int) bBean.getY());
                    al.addView(view, layoutParams3);
                }
            } else if ("Robot".equals(bBean.getItemType())) {//机器人

                if (!bBean.getPointName().equals("")) {
                    stringList = new ArrayList<>();
                    stringList.add(bBean.getPointName());
                    stringBuilder.append(bBean.getPointName());
                    i++;
                    if (i == 1) {
                        stringBuilder.append(",");
                    }
                }

                view = LayoutInflater.from(this).inflate(R.layout.layout_gongzuozhan, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_jiqiren);
                TextView textView = (TextView) view.findViewById(R.id.tv_lable);
                imageView.setImageResource(R.drawable.icon_30);
                textView.setText(bBean.getChartLabel());
                AbsoluteLayout.LayoutParams layoutParams1 = new AbsoluteLayout.LayoutParams((int) bBean.getW() * 2, (int) bBean.getH() * 2,
                        (int) bBean.getX(), (int) bBean.getY());
                al.addView(view, layoutParams1);

            }
            viewBBeanMap.put(view, bBean);
        }
        mapList.add(viewBBeanMap);


        Log.e(TAG, "succsess: " + stringBuilder.toString());

//        "00100111010111011101110"  
        //循环遍历转换成实体类对象
//        for (int i = 0; i < beanList.size(); i++) {
//            switch (i){
//                case 0://虚线
//                    bean = beanList.get(i);
////                    String itemType = bBean.getItemType();
////                    double ju_shi_H = bBean.getH();
////                    double bBeanW = bBean.getW();
////                    double bBeanX = bBean.getX();
////                    double bBeanY = bBean.getY();
//                    Log.e(TAG, "succsess: "+"bean:"+ bean);
//
//
//                    break;
//                case 1:
//                    bean = beanList.get(i);
//                    Log.e(TAG, "succsess: "+"bean:"+ bean);
//                    break;
//                case 2:
//                    bean = beanList.get(i);
//                    Log.e(TAG, "succsess: "+"bean:"+ bean);
//                    break;
//                case 3:
//                    bean = beanList.get(i);
//                    Log.e(TAG, "succsess: "+"bean:"+ bean);
//                    break;
//                case 4://工作站
//                    bean = beanList.get(i);
//                    Log.e(TAG, "succsess: "+"bean:"+ bean);
//
//
//                    break;
//                case 5://sample text
//                    bean = beanList.get(i);
//                    Log.e(TAG, "succsess: "+"bean:"+ bean);
//
//
//                    Log.e(TAG, "succsess: "+"bean.getW():"+ bean.getW());
//                    al.addView(view_shixian,layoutParams4);
//                    break;
//                case 6://实线
//                    bean = beanList.get(i);
//
//
//                    break;
//
//
//            }
//        }

    }

    @Override
    public void error() {

    }

    @Override
    public void succsess1(JSONObject jsonObject) {
//        {"a":"0","b":{"RB point":["2","1","00000000000000000000000000000000"]}}

        Log.e(TAG, "succsess1: " + "dianZhi:" + jsonObject.toString());

//        for (Map<View, Result.BBean> map : viewBBeanMap) {
        for (Map<View, Result.BBean> map : mapList) {
            for (Map.Entry<View, Result.BBean> map1 : map.entrySet()) {

                Log.e(TAG, "succsess1: " + "viewById:" + map1.getKey());
                Result.BBean bBean = map1.getValue();
                if (bBean.getItemType().equals("Robot")) {
                    ImageView viewById = (ImageView) map1.getKey().findViewById(R.id.iv_jiqiren);
                    Log.e(TAG, "succsess1: " + "viewById:" + viewById);
                    if (jsonObject.getString("a").equals("0")) {
                        Log.e(TAG, "succsess1: " + "jsonObject_一层:jsonObject.getString(\"b\")" + jsonObject.getString("b"));
                        if (jsonObject.getJSONObject("b").containsKey(bBean.getPointName())) {
                            JSONObject jsonObject1 = jsonObject.getJSONObject("b");
                            JSONArray jsonArray = jsonObject1.getJSONArray(bBean.getPointName());
                            Log.e(TAG, "succsess1: " + "jsonObject_三层层: jsonArray：" + jsonArray.size());
                            if (jsonArray.get(0).toString().equals("2") && jsonArray.get(1).toString().equals("1")) {
                                char[] chars = jsonArray.get(2).toString().toCharArray();
                                Log.e(TAG, "succsess1: " + "jsonObject_四层层:chars：" + chars.length);

                                Log.e(TAG, "succsess1: " + "chars:" + chars[0]);
                                Log.e(TAG, "succsess1: " + "viewBBeanMap:" + viewBBeanMap.size());

                                if (chars[1] == 1) {
                                    viewById.setImageResource(R.drawable.icon_28);
                                } else if (chars[0] == 1) {
                                    viewById.setImageResource(R.drawable.icon_30);
                                } else {
                                    viewById.setImageResource(R.drawable.icon_29);
                                }
                            }

                        }

                    } else {
                        return;
                    }
                }

            }

        }


    }


    @OnClick(R.id.button)
    public void onClick() {
        presenter.queryDianZhi(stringBuilder.toString());
    }
}
