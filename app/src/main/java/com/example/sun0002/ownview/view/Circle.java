package com.example.sun0002.ownview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static java.lang.System.in;

/**
 * Created by sun0002 on 2017/3/10.
 */

public class Circle extends View {
    private final  Paint paint;
    private final Context context;

    public Circle(Context context) {

        // TODO Auto-generated constructor stub
        this(context, null);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.paint = new Paint();
        this.paint.setAntiAlias(true); //消除锯齿
        this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        int center = getWidth()/2;
        int innerCircle = dip2px(context, 83); //设置内圆半径
        int ringWidth = dip2px(context, 5); //设置圆环宽度

        //绘制内圆
        this.paint.setARGB(155, 167, 190, 206);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center,center, innerCircle, this.paint);

        //绘制圆环
        this.paint.setARGB(255, 212 ,225, 233);
        this.paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center,center, innerCircle+1+ringWidth/2, this.paint);

        //绘制外圆
        this.paint.setARGB(155, 167, 190, 206);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center,center, innerCircle+ringWidth, this.paint);


        super.onDraw(canvas);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

//    private Paint paint;
//    //圆所在的矩形
//    private RectF oval;
//
//    public Circle(Context context) {
//        super(context);
//    }
//
//    public Circle(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        initView();
//    }
//
//
//
//    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    private void initView() {
//
//        paint = new Paint();
//    }
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        //实例化矩形
//        oval = new RectF(0,0,width,height);
//        //测量之后，一定要调用该方法，保存测量之后的宽度和高度
//        setMeasuredDimension(width,height);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        //绘制饼状图
//        canvas.drawArc(oval,-90,360,true,paint);
//    }

