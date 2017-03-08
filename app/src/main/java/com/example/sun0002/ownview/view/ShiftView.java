package com.example.sun0002.ownview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.sun0002.ownview.R;

/**
 * 作者：yq on 2017/3/7 22:29
 */

public class ShiftView extends View {
    private Paint juXing;
    private Paint mXuXian;
    private int xCoordinate;
    private int yCoordinate;
    private int width;
    private int height;
    private Paint mTuPian;

    public ShiftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public ShiftView(Context context) {
        this(context,null,0);
    }

    public ShiftView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    private void init() {
        juXing = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasJuXing(canvas);//画矩形
        canvasXuXianJuXing(canvas);//画矩形
//        canvasTupian(canvas);//画图片


    }

    private void canvasTupian(Canvas canvas) {
        mTuPian = new Paint();
        Paint wenzi = new Paint();
        //画图片，就是贴图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_30);

//        canvas.drawBitmap(bitmap, 66.25f,145.89f, mTuPian);

//        canvas.drawText("工作站",);
        RectF rectF = new RectF(66.25f,145.89f,66.25f+60,145.89f+60);
        canvas.drawBitmap(bitmap,null,rectF,mTuPian);
    }
    public static void drawImage(Canvas canvas, Bitmap blt, int x, int y, int w, int h, int bx, int by)
    {                                                        //x,y表示绘画的起点，
        Rect src = new Rect();// 图片
        Rect dst = new Rect();// 屏幕位置及尺寸
        //src 这个是表示绘画图片的大小
        src.left = bx;   //0,0
        src.top = by;
        src.right = bx + w;// mBitDestTop.getWidth();,这个是桌面图的宽度，
        src.bottom = by + h;//mBitDestTop.getHeight()/2;// 这个是桌面图的高度的一半
        // 下面的 dst 是表示 绘画这个图片的位置
        dst.left = x;    //miDTX,//这个是可以改变的，也就是绘图的起点X位置
        dst.top = y;    //mBitQQ.getHeight();//这个是QQ图片的高度。 也就相当于 桌面图片绘画起点的Y坐标
        dst.right = x + w;    //miDTX + mBitDestTop.getWidth();// 表示需绘画的图片的右上角
        dst.bottom = y + h;    // mBitQQ.getHeight() + mBitDestTop.getHeight();//表示需绘画的图片的右下角
        canvas.drawBitmap(blt, src, dst, null);//这个方法  第一个参数是图片原来的大小，第二个参数是 绘画该图片需显示多少。也就是说你想绘画该图片的某一些地方，而不是全部图片，第三个参数表示该图片绘画的位置

        src = null;
        dst = null;
    }

    private void canvasXuXianJuXing(Canvas canvas) {
        mXuXian = new Paint();
        mXuXian.setStyle(Paint.Style.STROKE);
        mXuXian.setColor(Color.DKGRAY);
        Path path = new Path();
        path.moveTo(358.25f,252.89f);
        path.lineTo(358.25f+413,252.89f);
        path.lineTo(358.25f+413,186+252.89f);
        path.lineTo(358.25f,186+252.89f);
        path.lineTo(358.25f,252.89f);
        PathEffect pathEffect = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        mXuXian.setPathEffect(pathEffect);
        canvas.drawPath(path,mXuXian);

    }

    private void canvasJuXing(Canvas canvas) {

        juXing.setColor(Color.GRAY);
        juXing.setStyle(Paint.Style.STROKE);//设置空心
        canvas.drawRect(358.25f,20.89f,358.25f+200,200+20.89f,juXing);
    }


}
