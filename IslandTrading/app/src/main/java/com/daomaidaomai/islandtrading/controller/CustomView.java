package com.daomaidaomai.islandtrading.controller;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.daomaidaomai.islandtrading.R;

public class CustomView extends View {

    private Paint mbackPaint;
    private Paint mForWordPaint;
    private Paint mTextPaint;
    private float mProgress = 0;
    private float mMax = 100;
    private int width;
    private int height;


    private float mStokeWidth = 20;
    private float mRadius = 140;
    private RectF mRectf;

    public CustomView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.CustomView);
            mRadius = a.getDimension(R.styleable.CustomView_radius, 50);//后面那个参数200是获取不到的时候去用的
            mStokeWidth = a.getDimension(R.styleable.CustomView_stroke_width, 5);
        }
        init();
    }

    /**
     * 计算绘制位置的
     */
    private void initRect() {
        if (mRectf == null) {
            mRectf = new RectF();
            int viewSize = (int) (mRadius * 2);
            int left = (width - viewSize) / 2;//左边的坐标
            int right = left + viewSize;
            int top = (height - viewSize) / 2;
            int bottom = top + viewSize;
            mRectf.set(left, top, right, bottom);//left：是矩形距离左边的X轴    top：是矩形距离上边的Y轴   right：是矩形距离右边的X轴    bottom：是矩形距离下边的Y轴
        }

    }

    private void init() {
        mbackPaint = new Paint();
        mbackPaint.setColor(Color.WHITE);
        mbackPaint.setAntiAlias(true);
        mbackPaint.setStyle(Paint.Style.STROKE);
        mbackPaint.setStrokeWidth(mStokeWidth);

        mForWordPaint = new Paint();
        mForWordPaint.setColor(0xFF66C796);
        mForWordPaint.setAntiAlias(true);
        mForWordPaint.setStyle(Paint.Style.STROKE);
        mForWordPaint.setStrokeWidth(mStokeWidth);

        mTextPaint = new Paint();
        mTextPaint.setColor(0xFF66C796);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(50);
        mTextPaint.setTextAlign(Paint.Align.CENTER);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
//    super.onDraw(canvas);
        initRect();

        float angle = mProgress / (float) mMax * 360;

        canvas.drawCircle(width / 2, height / 2, mRadius, mbackPaint);//cx：圆心的x坐标。 cy：圆心的y坐标。

//    oval :指定圆弧的外轮廓矩形区域。
//    startAngle: 圆弧起始角度，单位为度。
//    sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度。
//    useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。
//    paint: 绘制圆弧的画板属性，如颜色，是否填充等。
        canvas.drawArc(mRectf, -90, angle, false, mForWordPaint);

        //mX默认是字符串的左边在屏幕的位置，如果设置了
        //paint.setTextAlign(Paint.Align.CENTER);
        //那就是字符的中心，mY是指定这个字符baseline在屏幕上的位置。
        canvas.drawText(mProgress + "%", width / 2, height / 2 + 10, mTextPaint);
        if (mProgress < mMax) {
            mProgress += 2;

            invalidate();
        }


    }

    public void setYuanProgress(int a) {
        mProgress = (float) a;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getRealSize(widthMeasureSpec);//得到的是view的宽度 也就是整个空间的宽度
        height = getRealSize(heightMeasureSpec);//高度

        setMeasuredDimension(width, height);//保存
    }

    public int getRealSize(int measure) {
        int result = -1;

        int mode = MeasureSpec.getMode(measure);
        int size = MeasureSpec.getSize(measure);

        if (mode == MeasureSpec.AT_MOST || size == MeasureSpec.UNSPECIFIED) {
            result = (int) (mRadius * 2 + mStokeWidth);//半径*2+环形宽度的话就是整个环形显示宽度大小
        } else {
            result = size;
        }
        return result;
    }

}

