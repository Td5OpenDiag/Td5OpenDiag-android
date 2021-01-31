package com.github.anastr.speedviewlib

import android.content.Context
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet

/**
 * this Library build By Anas Altair
 * see it on [GitHub](https://github.com/anastr/SpeedView)
 */
open class ProgressiveGauge @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearGauge(context, attrs, defStyleAttr) {

    /** the shape  */
    private val path = Path()

    private val frontPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * change the color of progress.
     * @return foreground progress color.
     */
    var speedometerColor: Int
        get() = frontPaint.color
        set(speedometerColor) {
            frontPaint.color = speedometerColor
            invalidateGauge()
        }

    /**
     * change the color of background progress.
     * @return background progress color.
     */
    var speedometerBackColor: Int
        get() = backPaint.color
        set(speedometerBackColor) {
            backPaint.color = speedometerBackColor
            invalidateGauge()
        }

    init {
        init()
        initAttributeSet(context, attrs)
    }

    override fun defaultGaugeValues() {
        super.speedTextPosition = Position.CENTER
        super.unitUnderSpeedText = true
    }

    private fun init() {
        frontPaint.color = 0xFF00FFFF.toInt()
        backPaint.color = 0xffd6d7d7.toInt()
    }

    private fun initAttributeSet(context: Context, attrs: AttributeSet?) {
        if (attrs == null)
            return
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.LinearGauge, 0, 0)

        frontPaint.color = a.getColor(R.styleable.LinearGauge_sv_speedometerColor, frontPaint.color)
        backPaint.color = a.getColor(R.styleable.LinearGauge_sv_speedometerBackColor, backPaint.color)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val w = measuredWidth
        val h = measuredHeight
        if (orientation === Orientation.HORIZONTAL) {
            if (h > w / 2)
                setMeasuredDimension(w, w / 2)
            else
                setMeasuredDimension(h * 2, h)
        } else {
            if (w > h / 2)
                setMeasuredDimension(h / 2, h)
            else
                setMeasuredDimension(w, w * 2)
        }
    }

    override fun updateFrontAndBackBitmaps() {
        updateOrientation()
        val canvasBack = createBackgroundBitmapCanvas()
        val canvasFront = createForegroundBitmapCanvas()

        canvasBack.translate(padding.toFloat(), padding.toFloat())
        canvasBack.drawPath(path, backPaint)

        canvasFront.drawPath(path, frontPaint)
    }

    private fun updateOrientation() {
        if (orientation === Orientation.HORIZONTAL)
            updateHorizontalPath()
        else
            updateVerticalPath()
    }

    /**
     * to reset [path] for horizontal shape.
     */
    protected fun updateHorizontalPath() {
        path.reset()
        path.moveTo(0f, viewHeightNoPadding.toFloat())
        path.lineTo(0f, viewHeightNoPadding - viewHeightNoPadding * .1f)
        path.quadTo(viewWidthNoPadding * .75f, viewHeightNoPadding * .75f, viewWidthNoPadding.toFloat(), 0f)
        path.lineTo(viewWidthNoPadding.toFloat(), viewHeightNoPadding.toFloat())
        path.lineTo(0f, viewHeightNoPadding.toFloat())
    }

    /**
     * to reset [path] for vertical shape.
     */
    protected fun updateVerticalPath() {
        path.reset()
        path.moveTo(0f, viewHeightNoPadding.toFloat())
        path.lineTo(viewWidthNoPadding * .1f, viewHeightNoPadding.toFloat())
        path.quadTo(viewWidthNoPadding * .25f, viewHeightNoPadding * .25f, viewWidthNoPadding.toFloat(), 0f)
        path.lineTo(0f, 0f)
        path.lineTo(0f, viewHeightNoPadding.toFloat())
    }
}
