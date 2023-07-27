package com.example.android_movies_app.util

import android.content.Context
import android.R
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build.VERSION_CODES.R
import android.util.AttributeSet
import android.view.View
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.Transformation

class HorizontalDottedProgress: View {

    private val  mDotRadius = 5
    private val mBounceDotRadius = 8
    private val mDotPosition = 0
    private val mDotAmount = 10

    constructor(context: Context?) : super(context) {}
    constructor(context: Context? , attrs: AttributeSet) : super(context, attrs){}
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    protected override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.setColor(getResourcess().getColor(R.color.holo_orange_dark))
        createDot(canvas, paint)

    }

    protected override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnimation()
    }

    private fun createDot(canvas: Canvas, paint: Paint){
        for (i in 0 until mDotAmount){
            if (i == mDotPosition){
                canvas.drawCircle((10 + i * 20).toFloat(), mBounceDotRadius.toFloat(), mBounceDotRadius.toFloat(), paint)

            }else{
                canvas.drawCircle((10 + i * 20). toFloat(), mBounceDotRadius.toFloat(), mDotRadius.toFloat(), paint)
            }
         }
    }

    protected override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width: Int
        val height: Int
        val calcaulatedWidth = 20 * 9
        width = calcaulatedWidth
        height = mBounceDotRadius * 2
        setMeasuredDimension(width, height)
    }

    private fun startAnimation() {
        val bounceAnimation =
            BounceAnimation()
        bounceAnimation.duration = 100
        bounceAnimation.repeatCount = Animation.INFINITE
        bounceAnimation.interpolator = LinearInterpolator()
        bounceAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {
                mDotPosition++
                if (mDotPosition == mDotAmount) {
                    mDotPosition = 0
                }
                Log.d("INFOMETHOD", "On Animation Repeat")
            }
        })
        startAnimation(bounceAnimation)
    }

    private inner class BounceAnimation : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            super.applyTransformation(interpolatedTime, t)

            invalidate()
        }
    }

}