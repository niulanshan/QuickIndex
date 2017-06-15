package com.android.ls.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;

/**
 * Created by Administrator on 2017/6/13.
 */

public class QuickIndex extends View {
    String[] str = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"
    ,"Q","R","S","T","U","V","W","X","Y","Z"};
    private Paint paint;
    private Context context;
    private float cellHeight;
    private float cellWight;
    private QuickTouchLinstener linstener;
    private float downY;

    public QuickIndex(Context context) {
        this(context,null);
    }

    public QuickIndex(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QuickIndex(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init();
    }
    private int isSelected = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                int position = (int) (downY / cellHeight);
                isSelected = position;
                invalidate();
                linstener.onDown(str[position]);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();

                int newPosition = (int) (moveY / cellHeight);
                if(newPosition >str.length-1){
                    newPosition = str.length-1;
                }else if(newPosition <0){
                    newPosition = 0;
                }
                if(newPosition != isSelected){
                    linstener.onDown(str[newPosition]);
                    isSelected = newPosition;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                linstener.onRelease();
                break;
        }
        return true;
    }

    public void setSelectedLetter(String selectedLetter) {
        for (int i = 0; i < str.length; i++) {
            if(str[i].equals(selectedLetter)){
                isSelected = i;
                invalidate();
            }
        }
    }

    public interface QuickTouchLinstener{
        void onDown(String s);
        void onRelease();
    }
    public void setOnQuickTouchLinstener(QuickTouchLinstener linstener){
        this.linstener = linstener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //获取每个字母所在各自的高度
        cellHeight = (float) (h /26.0);
        cellWight = w;
        super.onSizeChanged(w, h, oldw, oldh);

    }

    /**
     * 初始化
     */
    private void init() {
        //设置反锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(getResources().getDimension(R.dimen.sp18));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0 ; i< str.length;i++) {
            float textHeight = getTextHeight(str[i], paint);


            if(i == isSelected){
                paint.setColor(Color.WHITE);
            }else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawText(str[i],cellWight/2,cellHeight/2+i*cellHeight + textHeight/2  ,paint);
        }
    }

    public float getTextHeight(String text,Paint paint) {
        Rect bounds = new Rect();
        paint.getTextBounds(text,0,text.length(),bounds);
        float height = bounds.height();
        return height;
    }


}
