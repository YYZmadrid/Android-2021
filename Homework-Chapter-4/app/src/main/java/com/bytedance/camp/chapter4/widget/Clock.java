package com.bytedance.camp.chapter4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;

public class Clock extends View {

    private static final int FULL_CIRCLE_DEGREE = 360;
    private static final int UNIT_DEGREE = 6;

    private static final float UNIT_LINE_WIDTH = 8; // 刻度线的宽度
    private static final int HIGHLIGHT_UNIT_ALPHA = 0xFF;
    private static final int NORMAL_UNIT_ALPHA = 0x80;

    private static final float HOUR_NEEDLE_LENGTH_RATIO = 0.5f; // 时针长度相对表盘半径的比例
    private static final float MINUTE_NEEDLE_LENGTH_RATIO = 0.65f; // 分针长度相对表盘半径的比例
    private static final float SECOND_NEEDLE_LENGTH_RATIO = 0.85f; // 秒针长度相对表盘半径的比例
    private static final float HOUR_NEEDLE_WIDTH = 17; // 时针的宽度
    private static final float MINUTE_NEEDLE_WIDTH = 12; // 分针的宽度
    private static final float SECOND_NEEDLE_WIDTH = 6; // 秒针的宽度

    private static final float CENTER_INNER_RADIUS_RATIO = 0.03f; //中心内半径
    private static final float CENTER_OUTER_RADIUS_RATIO = 0.08f; //中心外半径


    private Calendar calendar = Calendar.getInstance();

    private float radius = 0;
    private float centerX = 0;
    private float centerY = 0;
    private List<RectF> unitLinePositions = new ArrayList<>();
    private Paint unitPaint = new Paint();
    private Paint needlePaint = new Paint();
    private Paint numberPaint = new Paint();
    private Paint centerPaint = new Paint();
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private TimerHandler handler = null;

    public Clock(Context context) {
        super(context);
        handler = new TimerHandler(this);
        handler.sendEmptyMessageDelayed(1, 1000);
        init();
    }

    public Clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        handler = new TimerHandler(this);
        handler.sendEmptyMessageDelayed(1, 1000);
        init();
    }

    public Clock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handler = new TimerHandler(this);
        handler.sendEmptyMessageDelayed(1, 1000);
        init();
    }

    private void init() {
        unitPaint.setAntiAlias(true);
        unitPaint.setColor(Color.WHITE);
        unitPaint.setStrokeWidth(UNIT_LINE_WIDTH);
        unitPaint.setStrokeCap(Paint.Cap.ROUND);
        unitPaint.setStyle(Paint.Style.STROKE);

        needlePaint.setAntiAlias(true);
        needlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        needlePaint.setStrokeCap(Paint.Cap.ROUND);
        needlePaint.setColor(Color.WHITE);

        numberPaint.setAntiAlias(true);
        numberPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        numberPaint.setTextSize(50);
        numberPaint.setColor(Color.WHITE);
        numberPaint.setTextAlign(Paint.Align.LEFT);

        centerPaint.setAntiAlias(true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        configWhenLayoutChanged();
    }

    private void configWhenLayoutChanged() {
        float newRadius = Math.min(getWidth(), getHeight()) / 2f;
        if (newRadius == radius) {
            return;
        }
        radius = newRadius;
        centerX = getWidth() / 2f;
        centerY = getHeight() / 2f;

        // 当视图的宽高确定后就可以提前计算表盘的刻度线的起止坐标了
        for (int degree = 0; degree < FULL_CIRCLE_DEGREE; degree += UNIT_DEGREE) {
            double radians = Math.toRadians(degree);
            float startX = (float) (centerX + (radius * (1 - 0.05f)) * Math.cos(radians));
            float startY = (float) (centerX + (radius * (1 - 0.05f)) * Math.sin(radians));
            float stopX = (float) (centerX + radius * Math.cos(radians));
            float stopY = (float) (centerY + radius * Math.sin(radians));
            unitLinePositions.add(new RectF(startX, startY, stopX, stopY));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnit(canvas);
        drawTimeNeedles(canvas);
        drawTimeNumbers(canvas);
        drawCenter(canvas);
        handler.sendEmptyMessage(1);
    }

    // 绘制表盘上的刻度
    private void drawUnit(Canvas canvas) {
        for (int i = 0; i < unitLinePositions.size(); i++) {
            if (i % 5 == 0) {
                unitPaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);
            } else {
                unitPaint.setAlpha(NORMAL_UNIT_ALPHA);
            }
            RectF linePosition = unitLinePositions.get(i);
            canvas.drawLine(linePosition.left, linePosition.top, linePosition.right, linePosition.bottom, unitPaint);
        }
    }

    private void drawTimeNeedles(Canvas canvas) {
        Time time = getCurrentTime();
        int hour = time.getHours();
        int minute = time.getMinutes();
        int second = time.getSeconds();

        float startX, startY, endX, endY;
        double hourDegree = 30 * hour + 0.5 * (float)minute + (0.5 / 60) * (float)second - 90;
        double minuteDegree = 6 * minute + 0.1 * second - 90;
        double secondDegree = 6.0 * second - 90;



        // TODO 根据当前时间，绘制时针、分针、秒针
        //hour needle
        needlePaint.setStrokeWidth(HOUR_NEEDLE_WIDTH);
        startX = centerX;
        startY = centerY;
        endX = startX + radius * HOUR_NEEDLE_LENGTH_RATIO * (float)Math.cos(Math.toRadians(hourDegree));
        endY = startY + radius * HOUR_NEEDLE_LENGTH_RATIO * (float)Math.sin(Math.toRadians(hourDegree));
        canvas.drawLine(startX, startY, endX, endY, needlePaint);

        //minute
        needlePaint.setStrokeWidth(MINUTE_NEEDLE_WIDTH);
        startX = centerX;
        startY = centerY;
        endX = startX + radius * MINUTE_NEEDLE_LENGTH_RATIO * (float)Math.cos(Math.toRadians(minuteDegree));
        endY = startY + radius * MINUTE_NEEDLE_LENGTH_RATIO * (float)Math.sin(Math.toRadians(minuteDegree));
        canvas.drawLine(startX, startY, endX, endY, needlePaint);

        //second
        needlePaint.setStrokeWidth(SECOND_NEEDLE_WIDTH);
        startX = centerX;
        startY = centerY;
        endX = startX + radius * SECOND_NEEDLE_LENGTH_RATIO * (float)Math.cos(Math.toRadians(secondDegree));
        endY = startY + radius * SECOND_NEEDLE_LENGTH_RATIO * (float)Math.sin(Math.toRadians(secondDegree));
        canvas.drawLine(startX, startY, endX, endY, needlePaint);
    }

    private void drawTimeNumbers(Canvas canvas) {
        numberPaint.getFontMetrics(fontMetrics);
        float textHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < 12; i++) {
            float hourDegree = i * 30 - 60;
            String number = String.valueOf(i + 1);
            float textWidth = numberPaint.measureText(number);
            canvas.drawText(
                    number,
                    (float) (centerX + (radius * 0.85f ) * Math.cos(Math.toRadians(hourDegree))) - textWidth / 2.0f,
                    (float) (centerY + (radius * 0.85f ) * Math.sin(Math.toRadians(hourDegree))) + textHeight / 4.0f,
                    numberPaint
            );
        }
    }

    private void drawCenter(Canvas canvas){
        centerPaint.setStrokeWidth(CENTER_OUTER_RADIUS_RATIO * radius - CENTER_INNER_RADIUS_RATIO * radius);
        centerPaint.setStyle(Paint.Style.STROKE);
        centerPaint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, CENTER_INNER_RADIUS_RATIO * radius, centerPaint);
        centerPaint.setStyle(Paint.Style.FILL);
        centerPaint.setColor(Color.GRAY);
        canvas.drawCircle(centerX, centerY, CENTER_INNER_RADIUS_RATIO * radius, centerPaint);
    }

    // 获取当前的时间：时、分、秒
    private Time getCurrentTime() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        return new Time(
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));
    }

    private static final class TimerHandler extends Handler {

        private WeakReference<Clock> clockViewWeakReference;

        private TimerHandler(Clock clockView) {
            clockViewWeakReference = new WeakReference<>(clockView);
        }

        @Override
        public void handleMessage(Message msg) {
            Clock view = clockViewWeakReference.get();
            if (view != null) {
                view.getCurrentTime();
                view.invalidate();
                sendEmptyMessageDelayed(1, 1000); //1000ms
            }
        }
    }

    

}
