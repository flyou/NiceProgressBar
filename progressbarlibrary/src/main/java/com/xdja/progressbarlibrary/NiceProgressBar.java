package com.xdja.progressbarlibrary;

/**
 * Created by flyou on 2017/1/11.
 * VersionCode: 1
 * Desc:a nice progressbar for android
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;


public class NiceProgressBar extends View {
    /**
     * the default angle of max
     */
    private RectF mColorWheelRectangle;
    /**
     * the default WheelPaint
     */
    private Paint mDefaultWheelPaint;
    /**
     * show WheelPaint
     */
    private Paint mColorWheelPaint;
    /**
     * text Paint
     */
    private Paint textPaint;
    /**
     * show Radius
     */
    private float mColorWheelRadius;
    /**
     * circle Width
     */
    private float circleStrokeWidth;
    /**
     * text
     */
    private float mText;
    /**
     * Count for circle
     */
    private int mCount;
    /**
     * drawing angele ，always change in drawing
     */
    private float mSweepAnglePer;
    /**
     * the max SweepAngle
     */
    private float mSweepAngle;
    /**
     * text size
     */
    private int mTextSize;
    /**
     * animation for this view
     */
    private myProgressBarAnimation anim;
    /**
     * textColor for the text
     */
    private int textColor = 0xFF333333;
    /**
     * sweep wheel color
     */
    private int mWheelColor = 0xFF00CED1;
    /**
     * default circle color
     */
    private int mWheelColorDefault = 0xFF08080;
    /**
     * animation duration
     */
    private long mAnimDuration = 2000;
    /**
     * listener for progressbar
     */
    private ProgressBarListener mListener;

    /**
     * is show with percent et:66%、86%
     */
    private  boolean mIsShowWithPercent=false;

    public NiceProgressBar(Context context) {
        this(context, null);
        init();
    }

    public NiceProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public NiceProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mColorWheelRectangle = new RectF();
        circleStrokeWidth = DensityUtil.dip2px(getContext(), 10);

        mTextSize = DensityUtil.dip2px(getContext(), 40);
        mColorWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelPaint.setColor(mWheelColor);
        mColorWheelPaint.setStyle(Paint.Style.STROKE);
        mColorWheelPaint.setStrokeWidth(circleStrokeWidth);

        mDefaultWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDefaultWheelPaint.setColor(mWheelColorDefault);
        mDefaultWheelPaint.setStyle(Paint.Style.STROKE);
        mDefaultWheelPaint.setStrokeWidth(circleStrokeWidth);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textPaint.setColor(textColor);
        textPaint.setStyle(Style.FILL_AND_STROKE);
        textPaint.setTextAlign(Align.LEFT);
        textPaint.setTextSize(mTextSize);

        mText = 0;
        mSweepAngle = 0;

        anim = new myProgressBarAnimation();
        anim.setDuration(mAnimDuration);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(mColorWheelRectangle, -90, 360, false, mDefaultWheelPaint);
        canvas.drawArc(mColorWheelRectangle, -90, mSweepAnglePer, false, mColorWheelPaint);
        Rect bounds = new Rect();

        String textStr = mCount + "";
        if (mIsShowWithPercent){
            textStr=textStr+"%";
        }
        textPaint.getTextBounds(textStr, 0, textStr.length(), bounds);
        canvas.drawText(
                textStr,
                (mColorWheelRectangle.centerX())
                        - (textPaint.measureText(textStr) / 2),
                mColorWheelRectangle.centerY() + bounds.height() / 2,
                textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        mColorWheelRadius = min - circleStrokeWidth;
        mColorWheelRectangle.set(circleStrokeWidth, circleStrokeWidth,
                mColorWheelRadius, mColorWheelRadius);
    }


    /**
     * set animation duration
     */
    public NiceProgressBar SetAnimationDuration(long duration) {
        this.mAnimDuration = duration;
        anim.setDuration(mAnimDuration);
        return this;
    }

    /**
     * set max number show in end
     */
    public NiceProgressBar setTextMax(float max) {
        if (max>100){
            max=100;
        }
        mText = max;
        mSweepAngle=360*max/100;
        return this;
    }



    /**
     * set textcolor
     */
    public NiceProgressBar setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
        textPaint.setColor(textColor);
        return this;
    }

    /**
     * set color of circle showing
     */
    public NiceProgressBar setWheelColor(@ColorInt int wheelColor) {
        this.mWheelColor = wheelColor;
        mColorWheelPaint.setColor(mWheelColor);
        return this;
    }

    /**
     * set default or backgroundColor of circle
     */
    public NiceProgressBar setDefaultWheelColor(@ColorInt int defaultWheelColor) {
        this.mWheelColorDefault = defaultWheelColor;
        mDefaultWheelPaint.setColor(mWheelColorDefault);
        return this;
    }

    /**
     * show with percent
     * @param isShowWithPercent
     * @return
     */
   public NiceProgressBar showWithPercent(boolean isShowWithPercent) {
       mIsShowWithPercent=isShowWithPercent;
        return this;
    }


    /**
     * progressBar listener
     */
    public NiceProgressBar setProgressBarListener(ProgressBarListener listener) {
        mListener = listener;
        return this;
    }

    /**
     * start Animation and show
     */
    public void show() {
        this.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (mListener != null) {
                    mListener.onProgressBarStart();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mListener != null) {
                    mListener.onProgressBarComplete();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private class myProgressBarAnimation extends Animation {
        public myProgressBarAnimation() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                mSweepAnglePer = interpolatedTime * mSweepAngle;
                mCount = (int) (interpolatedTime * mText);
            } else {
                mSweepAnglePer = mSweepAngle;
                mCount = (int)mText;
            }
            postInvalidate();
        }
    }

    /**
     * the progressBar start or complete Listener
     */
    public interface ProgressBarListener {
        void onProgressBarComplete();

        void onProgressBarStart();
    }
}
