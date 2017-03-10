package com.example.sun0002.ownview.ui;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import java.util.Timer;
import java.util.TimerTask;

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


    private StringBuilder stringBuilderRobot = new StringBuilder();
    private StringBuilder stringBuilderRollingBed = new StringBuilder();
    private StringBuilder stringBuilderSTRollingBed = new StringBuilder();
    private StringBuilder stringBuilderSTRollingBedTop = new StringBuilder();
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
    private int i;
    private int i1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new ShiftPresenter(this);
        String num = "22";
        presenter.query(num);


    }

    @Override
    protected void onResume() {
        super.onResume();
        time();//定时器
    }

    private void time() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                String rollingBed = stringBuilderRollingBed.toString().substring(0, stringBuilderRollingBed.toString().length() - 1);
                Log.e(TAG, "run: "+"rollingBed:"+rollingBed);
                String robot = stringBuilderRobot.toString().substring(0, stringBuilderRobot.toString().length() - 1);
                Log.e(TAG, "run: "+"robot:"+robot);
                String sTRollingBed = stringBuilderSTRollingBed.toString().substring(0, stringBuilderSTRollingBed.toString().length() - 1);
                Log.e(TAG, "run: "+"sTRollingBed:"+sTRollingBed);
                String sTRollingBedTop = stringBuilderSTRollingBedTop.toString().substring(0, stringBuilderSTRollingBedTop.toString().length() - 1);
                Log.e(TAG, "run: "+"sTRollingBed:"+sTRollingBed);

                presenter.queryDianZhi(robot);
                presenter.queryDianZhi2(rollingBed);
                presenter.quaryDianzhiSTRollingBed(sTRollingBed);
                presenter.quaryDianzhiSTRollingBedTop(sTRollingBedTop);

            }
        };
        timer.schedule(timerTask, 5 * 1000, 5 * 1000);

    }


    @Override
    public void succsess(Result result) {
        if (result == null) {
            return;
        } else {
            beanList = result.getB();
            Log.e(TAG, "succsess: " + beanList.size());
        }
        viewBBeanMap = new HashMap<>();
        mapList = new ArrayList<>();
        for (Result.BBean bBean : beanList) {
            if ("GlassRoom".equals(bBean.getItemType())) {
                if (bBean.getStrBorderStyle() == 0) {//实线
                    Log.e(TAG, "succsess: " + "bean:" + bBean);
                    view = LayoutInflater.from(this).inflate(R.layout.layout_shixian, null);
                    AbsoluteLayout.LayoutParams layoutParams4 = new AbsoluteLayout.LayoutParams((int) (bBean.getW() * (1.6)), (int) (bBean.getH() * (1.6)),
                            (int) (bBean.getX()* (1.6)), (int) (bBean.getY()* (1.6)));
                    al.addView(view, layoutParams4);
                } else {//虚线
                    view = LayoutInflater.from(this).inflate(R.layout.layout_xuxian, null);
                    AbsoluteLayout.LayoutParams layoutParams3 = new AbsoluteLayout.LayoutParams((int) (bBean.getW() * (1.6)), (int) (bBean.getH() * (1.6)),
                            (int)  (bBean.getX()* (1.6)), (int) (bBean.getY()* (1.6)));
                    al.addView(view, layoutParams3);
                }
            } else if ("Robot".equals(bBean.getItemType())) {//机器人

                if (!bBean.getPointName().equals("")) {
                    stringBuilderRobot.append(bBean.getPointName());
                    stringBuilderRobot.append(",");

                }

                view = LayoutInflater.from(this).inflate(R.layout.layout_gongzuozhan, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_jiqiren);
                TextView textView = (TextView) view.findViewById(R.id.tv_lable);
                imageView.setImageResource(R.drawable.icon_30);
                textView.setText(bBean.getChartLabel());
                AbsoluteLayout.LayoutParams layoutParams1 = new AbsoluteLayout.LayoutParams((int) (bBean.getW() * (1.6)), (int) (bBean.getH() * (1.6)),
                        (int) (bBean.getX()* (1.6)), (int) (bBean.getY()* (1.6)));
                al.addView(view, layoutParams1);

            } else if ("RollingBed".equals(bBean.getItemType())) {//变色矩形
                view = LayoutInflater.from(this).inflate(R.layout.layout_rolling_bed, null);
                if (!bBean.getPointName().equals("")) {
                    stringBuilderRollingBed.append(bBean.getPointName());
                    stringBuilderRollingBed.append(",");

                }

                //更换箭头
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_jiantou);
                if (bBean.getStandardDirection() == 0){//朝右
                    imageView.setImageResource(R.drawable.icon_53);
                }else if (bBean.getStandardDirection() == 1){
                    imageView.setImageResource(R.drawable.icon_52);
                }
                //矩形旋转
                if (bBean.getRotateAngle() == 2) {//朝左
                    ObjectAnimator oaAnimator1= ObjectAnimator.ofFloat(view, "rotation", 0, 90);
                    oaAnimator1.start();
                }else if (bBean.getRotateAngle() == 4){
                    ObjectAnimator oaAnimator1= ObjectAnimator.ofFloat(view, "rotation", 0, -90);
                    oaAnimator1.start();//注意在销毁时取消动画
                }


                i = (int) (bBean.getW() * (1.6));
                Log.e(TAG, "succsess: "+"i sdsdsds   ................"+i);
                i1 = (int) (bBean.getH() * (1.6));
                AbsoluteLayout.LayoutParams layoutParams5 = new AbsoluteLayout.LayoutParams((int) (156 * (1.6)), (int) (60 * (1.6))+10,
                        (int)(bBean.getX()* (1.6)), (int) (bBean.getY()* (1.6)));
                al.addView(view, layoutParams5);
            }else if (bBean.getItemType().equals("STLBed")){
                if (!bBean.getPointName().equals("")) {
                    stringBuilderSTRollingBedTop.append(bBean.getPointName());
                    stringBuilderSTRollingBedTop.append(",");
                }
                if (!bBean.getRollingBed().getPointName().equals("")){
                    stringBuilderSTRollingBed.append(bBean.getRollingBed().getPointName());
                    stringBuilderSTRollingBed.append(",");
                }

                view = LayoutInflater.from(this).inflate(R.layout.layout_st_rolling_bed, null);
                Log.e(TAG, "succsess: "+"view.........."+view);
                View st_view = view.findViewById(R.id.st_view);
                Log.e(TAG, "succsess: "+"st_view"+st_view);


//                st_view.setLayoutParams(new RelativeLayout.LayoutParams((int) (156 * (1.6)), (int) (60 * (1.6))*2 + 10));
                View st_yuanhuan = st_view.findViewById(R.id.st_yuanhuan);
                RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams((int) (60 * (1.6)*2.5), (int) (60 * (1.6)*2.5));
                layoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT);
                st_yuanhuan.setLayoutParams(layoutParams1);
                View st_cir_rb = st_view.findViewById(R.id.st_cir_rb);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) (156 * (1.6)), (int) (60 * (1.6)) + 10);

                layoutParams2.addRule(RelativeLayout.CENTER_IN_PARENT);
                st_cir_rb.setLayoutParams(layoutParams2);
                TextView st_title = (TextView) view.findViewById(R.id.st_title);
                Log.e(TAG, "succsess: "+"st_title:"+st_title+"   "+"bBean.getChartLabel():"+bBean.getChartLabel());
                st_title.setText(bBean.getChartLabel());
                ImageView st_iv_jiantou = (ImageView) view.findViewById(R.id.st_iv_jiantou);

                TextView rb_title = (TextView) st_view.findViewById(R.id.rb_title);
                rb_title.setText(bBean.getRollingBed().getChartLabel());
                ImageView iv_jiantou = (ImageView) st_view.findViewById(R.id.iv_jiantou);

                if (bBean.getStandardDirection() == 0){
                    st_iv_jiantou.setImageResource(R.drawable.icon_62);

                }else {
                    st_iv_jiantou.setImageResource(R.drawable.icon_63);
                }

                if (!TextUtils.isEmpty(bBean.getStrBins()) && bBean.getStrBins().split(",").length > 0){
                    st_view.setVisibility(View.VISIBLE);

                }else {
                    st_view.setVisibility(View.GONE);

                    return;
                }



                if (bBean.getStandardDirection() == 0){
                    iv_jiantou.setImageResource(R.drawable.icon_53);
                }else {
                    iv_jiantou.setImageResource(R.drawable.icon_52);
                }


                LinearLayout ll_st = (LinearLayout) view.findViewById(R.id.ll_st);
                ll_st.setLayoutParams(new LinearLayout.LayoutParams((int)(156 * (1.6)),(int) (60 * (1.6)) + 10));
                View st_fra_ll = view.findViewById(R.id.st_fra_ll);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int)(60 * (1.6)*1.5), (int) (bBean.getH() * (1.6) - ll_st.getHeight()));
                layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                st_fra_ll.setLayoutParams(layoutParams);
                st_view.setLayoutParams(new FrameLayout.LayoutParams((int) (156 * (1.6)), (int) (60 * (1.6))*3 + 10));
                AbsoluteLayout.LayoutParams layoutParams6 = new AbsoluteLayout.LayoutParams((int)(156 * (1.6)), (int) (bBean.getH() * (1.6)),
                        (int)(bBean.getX()* (1.6)), (int) (bBean.getY()* (1.6)));
                al.addView(view, layoutParams6);

                Log.e(TAG, "succsess: "+"(int) (bBean.getH() * (1.6)" +(int) (bBean.getH() * (1.6)));


            }
            viewBBeanMap.put(view,bBean);

        }
        mapList.add(viewBBeanMap);


        Log.e(TAG, "succsess ...................: " + "viewBBeanMap:" + viewBBeanMap.size());


//        "00100111010111011101110"  
        //循环遍历转换成实体类对象

    }


    /**
     * Robot
     * @param jsonObject
     */
    @Override
    public void succsessRobot(JSONObject jsonObject) {

//        {"a":"0","b":{"RB point":["2","1","00000000000000000000000000000000"]}}

        Log.e(TAG, "succsessRobot: " + "dianZhi:" + jsonObject.toString());

//        for (Map<View, Result.BBean> map : viewBBeanMap) {
        for (Map<View, Result.BBean> map : mapList) {
            for (Map.Entry<View, Result.BBean> map1 : map.entrySet()) {

                Log.e(TAG, "succsessRobot: " + "viewById:" + map1.getKey());
                Result.BBean bBean = map1.getValue();
                if (bBean.getItemType().equals("Robot")) {
                    ImageView viewById = (ImageView) map1.getKey().findViewById(R.id.iv_jiqiren);
                    Log.e(TAG, "succsessRobot: " + "viewById:" + viewById);
                    if (jsonObject.getString("a").equals("0")) {
                        Log.e(TAG, "succsessRobot: " + "jsonObject_一层:jsonObject.getString(\"b\")");
                        if (jsonObject.getJSONObject("b").containsKey(bBean.getPointName())) {
                            JSONObject jsonObject1 = jsonObject.getJSONObject("b");
                            JSONArray jsonArray = jsonObject1.getJSONArray(bBean.getPointName());
                            Log.e(TAG, "succsessRobot: " + "jsonObject_三层层: jsonArray：" + jsonArray.size());
                            if (jsonArray.get(0).toString().equals("2") && jsonArray.get(1).toString().equals("1")) {
                                char[] chars = jsonArray.get(2).toString().toCharArray();
                                Log.e(TAG, "succsessRobot: " + "jsonObject_四层层:chars：" + chars.length);

                                Log.e(TAG, "succsessRobot: " + "chars:" + chars[0]);
                                Log.e(TAG, "succsessRobot: " + "viewBBeanMap:" + viewBBeanMap.size());

                                if (chars[1] == '1') {
                                    viewById.setImageResource(R.drawable.icon_28);

                                } else if (chars[0] == '1') {
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

    /**
     * RollingBed
     * @param jsonObject
     */
    @Override
    public void succsessRBed(JSONObject jsonObject) {

        Log.e(TAG, "succsessRBed: " + "dianZhi:" + jsonObject.toString());
//        dianZhi:{"a":"0","b":{"sample point":["2","1","11000110111111111011000111110111"],
//                              "fdgfdfdgfd":["2","1","01011110100010101100101101100101"]}}


        for(Map<View, Result.BBean> map : mapList){
            for (Map.Entry<View, Result.BBean> map1:map.entrySet()){

                View view1 = map1.getKey();
                Log.e(TAG, "succsessRBed: "+"view1:"+view1);
                Result.BBean bBean = map1.getValue();
                if (bBean.getItemType().equals("RollingBed")){
                    ImageView xiaceng_left_lock = (ImageView) view1.findViewById(R.id.xiaceng_left_lock);
                    ImageView xiaceng_model_1 = (ImageView) view1.findViewById(R.id.xiaceng_model_1);
                    Log.e(TAG, "succsessRBed: "+ "xiaceng_model_1_chushihua:"+xiaceng_model_1);
                    ImageView xiaceng_car = (ImageView) view1.findViewById(R.id.xiaceng_car);
                    Log.e(TAG, "succsessRBed: "+ "xiaceng_car_chushihua:"+xiaceng_car);

                    ImageView xiaceng_center = (ImageView) view1.findViewById(R.id.xiaceng_center);
                    ImageView xiaceng_model2 = (ImageView) view1.findViewById(R.id.xiaceng_model2);
                    ImageView xiaceng_right_lock = (ImageView) view1.findViewById(R.id.xiaceng_right_lock);
                    if (jsonObject.getString("a").equals("0")){
                        if (jsonObject.getJSONObject("b").containsKey(bBean.getPointName())){

                            JSONArray jsonArray = jsonObject.getJSONObject("b").getJSONArray(bBean.getPointName());
                            if (!TextUtils.isEmpty(jsonArray.toString()) && jsonArray.size() > 2 && jsonArray.get(0).equals("2") && jsonArray.get(1).equals("1")){

                                Log.e(TAG, "succsessRBed: "+"jsonArray.get(2).toString():"+jsonArray.get(2).toString());
                                char[] chars = jsonArray.get(2).toString().toCharArray();
                                if (chars != null && chars.length >0){
                                    List<Result.BBean.PointValueColBeanX> pointValueCol = bBean.getPointValueCol();
                                    for(Result.BBean.PointValueColBeanX pointValueColBeanX : pointValueCol){
                                        if (pointValueColBeanX.getKey().equals("IsAuto")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_model_1.setBackgroundColor(Color.GREEN);
                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("IsManual")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_model_1.setBackgroundColor(Color.WHITE);
                                            }
                                        }

                                        if (pointValueColBeanX.getKey().equals("IsPrepairing")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                Log.e(TAG, "succsessRBed: "+ "xiaceng_model_1:"+xiaceng_model_1);
                                                xiaceng_model_1.setBackgroundColor(Color.parseColor("#FFA500"));
                                            }
                                        }

                                        //TODO 待验证
                                        if (pointValueColBeanX.getKey().equals("WarningB")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_model2.setBackgroundColor(Color.RED);
                                            }
                                        }else {
                                            if (pointValueColBeanX.getKey().equals("WarningC")){
                                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anInt] == '1'){
                                                    xiaceng_model2.setBackgroundColor(Color.YELLOW);
                                                }else {
                                                    xiaceng_model2.setBackgroundColor(Color.GREEN);
                                                }
                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("EntryClosed")){//入口关闭
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_left_lock.setImageResource(R.drawable.icon_32);
                                            }else {
                                                xiaceng_left_lock.setImageBitmap(null);
                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("FWDRotatingHighSpeed")){//正向高速
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_center.setImageResource(R.drawable.icon_60);

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("REVRotatingHighSpeed")){//反向高速
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_center.setImageResource(R.drawable.icon_50);

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("FWDRotatingLowSpeed")){//正向高速
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_center.setImageResource(R.drawable.icon_61);

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("REVRotatingLowSpeed")){//反向高速
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_center.setImageResource(R.drawable.icon_61);

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("Exists")){//目标存在
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_car.setVisibility(View.VISIBLE);
                                            }else {
                                                xiaceng_car.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("ExitClosed")){//出口关闭
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_right_lock.setImageResource(R.drawable.icon_32);
                                            }else {
                                                xiaceng_right_lock.setImageBitmap(null);
                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("IsVoidMode")){//
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                xiaceng_right_lock.setImageResource(R.drawable.icon_32);
                                            }else {
                                                xiaceng_model_1.setBackgroundColor(Color.GREEN);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }


            }
        }


    }

    /**
     * STLBed
     * @param jsonObject
     */
    @Override
    public void succsessSTBed(JSONObject jsonObject) {
        if (jsonObject != null){
            Log.e(TAG, "succsessSTBed: "+"jsonObject:"+jsonObject.toJSONString());
//            {"a":"0","b":{"sample point":["2","1","00010001011110101001110000100011"]}}
            if (jsonObject.getString("a").equals("0")){
                for (Map<View,Result.BBean> map : mapList){
                    for(Map.Entry<View,Result.BBean> map1 : map.entrySet()){
                        View keyView = map1.getKey();
                        Result.BBean bBean = map1.getValue();

                        if (bBean.getItemType().equals("STLBed")){

                            View st_view = keyView.findViewById(R.id.st_view);
                            View ll_st = keyView.findViewById(R.id.ll_st);

                            Log.e(TAG, "succsessSTBed: "+"ll_st:"+ll_st);
                            Log.e(TAG, "succsessSTBed: "+"st_view:"+st_view);
                            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) st_view.getLayoutParams();
                            presenter.quaryDianZhiOfST(bBean.getRollingBed().getPointName(),st_view,bBean);
                            Log.e(TAG, "succsessSTBed: "+"bBean.getStrBins():"+bBean.getStrBins());
                            if (TextUtils.isEmpty(bBean.getStrBins())){
                                return;
                            }
                            String[] split = bBean.getStrBins().split(",");
                            int height = (int) ((bBean.getH() - ll_st.getHeight()- st_view.getHeight())/100);
                            Log.e(TAG, "succsessSTBed: "+"height:"+height);
                            Log.e(TAG, "succsessSTBed: "+"height:"+bBean.getRollingBed().getPointName());
                            if (jsonObject.getJSONObject("b").containsKey(bBean.getRollingBed().getPointName())){
                                Log.e(TAG, "succsessSTBed: "+"进入。。。。。。。。。:");
                                JSONArray jsonArray = jsonObject.getJSONObject("b").getJSONArray(bBean.getRollingBed().getPointName());
                                Log.e(TAG, "succsessSTBed: "+"jsonArray:"+jsonArray.get(1));
                                if (jsonArray !=null && jsonArray.size() >2 && jsonArray.get(0).equals("2") && jsonArray.get(1).equals("1")){
                                    Log.e(TAG, "succsessSTBed: "+"进入了。。。。。。。。:");
                                    char[] chars = jsonArray.get(2).toString().toCharArray();
                                    Log.e(TAG, "succsessSTBed: "+"chars"+chars.length);
                                    List<Result.BBean.PointValueColBeanX> pointValueCol = bBean.getPointValueCol();

                                    for(Result.BBean.PointValueColBeanX pointValueColBeanX:pointValueCol){
                                        if (pointValueColBeanX.getKey().equals("Position1")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                if (split.length<1){
                                                    return;
                                                }
                                                layoutParams.leftMargin = st_view.getLeft();
                                                layoutParams.topMargin = height*Integer.parseInt(split[0]);
                                                st_view.setLayoutParams(layoutParams);
                                                Log.e(TAG, "succsessSTBed: "+"Position1");

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("Position2")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                if (split.length<2){
                                                    return;
                                                }
                                                layoutParams.leftMargin = st_view.getLeft();
                                                layoutParams.topMargin = height*Integer.parseInt(split[1]);
                                                st_view.setLayoutParams(layoutParams);
                                                Log.e(TAG, "succsessSTBed: "+"Position2");

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("Position3")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                if (split.length<3){
                                                    return;
                                                }
                                                layoutParams.leftMargin = st_view.getLeft();
                                                layoutParams.topMargin = height*Integer.parseInt(split[2]);
                                                st_view.setLayoutParams(layoutParams);
                                                Log.e(TAG, "succsessSTBed: "+"Position3");

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("Position4")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                if (split.length<4){
                                                    return;
                                                }
                                                layoutParams.leftMargin = st_view.getLeft();
                                                layoutParams.topMargin = height*Integer.parseInt(split[3]);
                                                st_view.setLayoutParams(layoutParams);
                                                Log.e(TAG, "succsessSTBed: "+"Position4");

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("Position5")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                if (split.length<5){
                                                    return;
                                                }
                                                layoutParams.leftMargin = st_view.getLeft();
                                                layoutParams.topMargin = height*Integer.parseInt(split[4]);
                                                st_view.setLayoutParams(layoutParams);
                                                Log.e(TAG, "succsessSTBed: "+"Position5");

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("Position6")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                if (split.length<6){
                                                    return;
                                                }
                                                layoutParams.leftMargin = st_view.getLeft();
                                                layoutParams.topMargin = height*Integer.parseInt(split[5]);
                                                st_view.setLayoutParams(layoutParams);
                                                Log.e(TAG, "succsessSTBed: "+"Position6");

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("Position7")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                if (split.length<7){
                                                    return;
                                                }
                                                layoutParams.leftMargin = st_view.getLeft();
                                                layoutParams.topMargin = height*Integer.parseInt(split[6]);
                                                st_view.setLayoutParams(layoutParams);
                                                Log.e(TAG, "succsessSTBed: "+"Position7");

                                            }
                                        }
                                        if (pointValueColBeanX.getKey().equals("Position8")){
                                            int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                            if (chars[anInt] == '1'){
                                                if (split.length<8){
                                                    return;
                                                }
                                                layoutParams.leftMargin = st_view.getLeft();
                                                layoutParams.topMargin = height*Integer.parseInt(split[7]);
                                                st_view.setLayoutParams(layoutParams);
                                                Log.e(TAG, "succsessSTBed: "+"Position8");

                                            }
                                        }


                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

    }

    @Override
    public void succsessSTBedInit(JSONObject jsonObject,String str, View view, Result.BBean  bBean) {
        if (jsonObject !=null && !TextUtils.isEmpty(str) && view !=null && bBean != null){
            Log.e(TAG, "succsessSTBedInit: "+"jsonObject:"+jsonObject.toJSONString());
            if (jsonObject.getString("a").equals("0") && jsonObject.getJSONObject("b").containsKey(str)){
                Log.e(TAG, "succsessSTBedInit: "+"第二层:"+"进入");
                JSONArray jsonArray = jsonObject.getJSONObject("b").getJSONArray(str);
                Log.e(TAG, "succsessSTBedInit: "+"jsonArray:"+jsonArray);
                if (!TextUtils.isEmpty(jsonArray.toString())&& jsonArray.size()> 2 && jsonArray.get(0).equals("2") && jsonArray.get(1).equals("1")){
                    char[] chars = jsonArray.get(2).toString().toCharArray();
                    Log.e(TAG, "succsessSTBedInit: "+"chars:"+chars.length);
                    if (chars!= null && chars.length >0){
                        List<Result.BBean.PointValueColBeanX> pointValueCol = bBean.getPointValueCol();
                        ImageView xiaceng_left_lock = (ImageView) view.findViewById(R.id.xiaceng_left_lock);
                        ImageView xiaceng_model_1 = (ImageView) view.findViewById(R.id.xiaceng_model_1);
                        Log.e(TAG, "succsessRBed: "+ "xiaceng_model_1_chushihua:"+xiaceng_model_1);
                        ImageView xiaceng_car = (ImageView) view.findViewById(R.id.xiaceng_car);
                        Log.e(TAG, "succsessRBed: "+ "xiaceng_car_chushihua:"+xiaceng_car);

                        ImageView xiaceng_center = (ImageView) view.findViewById(R.id.xiaceng_center);
                        ImageView xiaceng_model2 = (ImageView) view.findViewById(R.id.xiaceng_model2);
                        ImageView xiaceng_right_lock = (ImageView) view.findViewById(R.id.xiaceng_right_lock);
                        for(Result.BBean.PointValueColBeanX pointValueColBeanX : pointValueCol){
                            if (pointValueColBeanX.getKey().equals("IsAuto")){
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_model_1.setBackgroundColor(Color.GREEN);
                                }
                            }
                            if (pointValueColBeanX.getKey().equals("IsManual")){
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_model_1.setBackgroundColor(Color.WHITE);
                                }
                            }

                            if (pointValueColBeanX.getKey().equals("IsPrepairing")){
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    Log.e(TAG, "succsessRBed: "+ "xiaceng_model_1:"+xiaceng_model_1);
                                    xiaceng_model_1.setBackgroundColor(Color.parseColor("#FFA500"));
                                }
                            }

                            //TODO 待验证
                            if (pointValueColBeanX.getKey().equals("WarningB")){
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_model2.setBackgroundColor(Color.RED);
                                }
                            }else {
                                if (pointValueColBeanX.getKey().equals("WarningC")){
                                    int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                    if (chars[anInt] == '1'){
                                        xiaceng_model2.setBackgroundColor(Color.YELLOW);
                                    }else {
                                        xiaceng_model2.setBackgroundColor(Color.GREEN);
                                    }
                                }
                            }
                            if (pointValueColBeanX.getKey().equals("EntryClosed")){//入口关闭
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_left_lock.setImageResource(R.drawable.icon_32);
                                }else {
                                    xiaceng_left_lock.setImageBitmap(null);
                                }
                            }
                            if (pointValueColBeanX.getKey().equals("FWDRotatingHighSpeed")){//正向高速
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_center.setImageResource(R.drawable.icon_60);

                                }
                            }
                            if (pointValueColBeanX.getKey().equals("REVRotatingHighSpeed")){//反向高速
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_center.setImageResource(R.drawable.icon_50);

                                }
                            }
                            if (pointValueColBeanX.getKey().equals("FWDRotatingLowSpeed")){//正向高速
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_center.setImageResource(R.drawable.icon_61);

                                }
                            }
                            if (pointValueColBeanX.getKey().equals("REVRotatingLowSpeed")){//反向高速
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_center.setImageResource(R.drawable.icon_61);

                                }
                            }
                            if (pointValueColBeanX.getKey().equals("Exists")){//目标存在
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_car.setVisibility(View.VISIBLE);
                                }else {
                                    xiaceng_car.setVisibility(View.INVISIBLE);
                                }
                            }
                            if (pointValueColBeanX.getKey().equals("ExitClosed")){//出口关闭
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_right_lock.setImageResource(R.drawable.icon_32);
                                }else {
                                    xiaceng_right_lock.setImageBitmap(null);
                                }
                            }
                            if (pointValueColBeanX.getKey().equals("IsVoidMode")){//
                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                if (chars[anInt] == '1'){
                                    xiaceng_right_lock.setImageResource(R.drawable.icon_32);
                                }else {
                                    xiaceng_model_1.setBackgroundColor(Color.GREEN);
                                }
                            }
                        }
                    }

                    }
                }
            }
        }

    @Override
    public void succsessSTbedTop(JSONObject jsonObject) {
        if (jsonObject!=null){
            Log.e(TAG, "succsessSTbedTop: "+"json:"+jsonObject.toJSONString());
            if (jsonObject.getString("a").equals("0")){
                for (Map<View,Result.BBean> map : mapList){
                    for (Map.Entry<View,Result.BBean> map1:map.entrySet()){
                        View keyView = map1.getKey();
                        Result.BBean bBean = map1.getValue();
                        if (bBean.getItemType().equals("STLBed")){
                            if (jsonObject.getJSONObject("b").containsKey(bBean.getPointName())){
                                JSONArray array = jsonObject.getJSONObject("b").getJSONArray(bBean.getPointName());
                                Log.e(TAG, "succsessSTbedTop: "+"array:"+array.size());
                                if (array.size()>2 && array!=null && array.get(0).equals("2") && array.get(1).equals("1")){
                                    char[] chars = array.toString().toCharArray();
                                    if (chars!=null && chars.length>0){
                                        ImageView st_xiaceng_model_1 = (ImageView) keyView.findViewById(R.id.st_xiaceng_model_1);
                                        ImageView st_xiaceng_car = (ImageView) keyView.findViewById(R.id.st_xiaceng_car);
                                        ImageView st_xiaceng_model2 = (ImageView) keyView.findViewById(R.id.st_xiaceng_model2);
                                        List<Result.BBean.PointValueColBeanX> pointValueCol = bBean.getPointValueCol();
                                        for (Result.BBean.PointValueColBeanX pointValueColBeanX : pointValueCol){
                                            if (pointValueColBeanX.getKey().equals("WarningB")){
                                                int anInt = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anInt] == '1'){
                                                    st_xiaceng_model2.setBackgroundColor(Color.RED);
                                                }else {
                                                    if (pointValueColBeanX.getKey().equals("WarningC")){
                                                        int anIntWarningC = Integer.parseInt(pointValueColBeanX.getValue());
                                                        if (chars[anIntWarningC] == '1'){
                                                            st_xiaceng_model2.setBackgroundColor(Color.YELLOW);
                                                        }else {

                                                            st_xiaceng_model2.setBackgroundColor(Color.GREEN);
                                                        }
                                                    }
                                                }
                                            }

                                            if (pointValueColBeanX.getKey().equals("IsAuto")){
                                                int anIntIsAuto = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anIntIsAuto] == '1'){
                                                    st_xiaceng_model_1.setBackgroundColor(Color.GREEN);
                                                }
                                            }
                                            if (pointValueColBeanX.getKey().equals("IsManual")){
                                                int anIntIsManual = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anIntIsManual] == '1'){
                                                    st_xiaceng_model_1.setBackgroundColor(Color.WHITE);
                                                }
                                            }
                                            if (pointValueColBeanX.getKey().equals("IsPrepairing")){
                                                int anIntIsPrepairing = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anIntIsPrepairing] == '1'){
                                                    st_xiaceng_model_1.setBackgroundColor(Color.parseColor("#FFA500"));
                                                }
                                            }
                                            if (pointValueColBeanX.getKey().equals("IsVoidMode")){
                                                int anIntIsVoidMode = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anIntIsVoidMode] == '1'){
                                                    st_xiaceng_model_1.setBackgroundColor(Color.GREEN);
                                                }
                                            }
                                            if (pointValueColBeanX.getKey().equals("FWDRotatingHighSpeed")){//正向高速
                                                int anIntFWDRotatingHighSpeed = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anIntFWDRotatingHighSpeed] == '1'){
                                                    if (bBean.getStandardDirection() == 0) {
                                                        st_xiaceng_car.setImageResource(R.drawable.icon_64);
                                                    }
                                                }
                                            }
                                            if (pointValueColBeanX.getKey().equals("REVRotatingHighSpeed")){//反向高速
                                                int anIntFWDRotatingHighSpeed = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anIntFWDRotatingHighSpeed] == '1'){
                                                    if (bBean.getStandardDirection() == 0) {
                                                        st_xiaceng_car.setImageResource(R.drawable.icon_65);
                                                    }
                                                }
                                            }
                                            if (pointValueColBeanX.getKey().equals("FWDRotatingLowSpeed")){//正向低速
                                                int anIntFWDRotatingHighSpeed = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anIntFWDRotatingHighSpeed] == '1'){
                                                    if (bBean.getStandardDirection() == 0) {
                                                        st_xiaceng_car.setImageResource(R.drawable.icon_66);
                                                    }

                                                }
                                            }
                                            if (pointValueColBeanX.getKey().equals("REVRotatingLowSpeed")){//反向低速
                                                int anIntFWDRotatingHighSpeed = Integer.parseInt(pointValueColBeanX.getValue());
                                                if (chars[anIntFWDRotatingHighSpeed] == '1'){
                                                    if (bBean.getStandardDirection() == 0) {
                                                        st_xiaceng_car.setImageResource(R.drawable.icon_67);
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void error() {

    }













    //****************************  网络请求方法  *************************************

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

    @OnClick(R.id.button)
    public void onClick() {


    }
}
