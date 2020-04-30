package com.ffeiyue.rowingview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import kotlin.math.PI
import kotlin.math.atan2

class RiverView : ViewGroup {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private val mPaint = Paint().apply { style = Paint.Style.STROKE }

    private lateinit var mPath: Path

    lateinit var mRowingView: RowingView

    private lateinit var mPathMeasure: PathMeasure

    private val mPos = FloatArray(2)
    private val mTan = FloatArray(2)

    init {
        setWillNotDraw(false)
    }

    fun setPath(path: Path) {
        mPath = path
        mPathMeasure = PathMeasure(mPath, false)
    }

    fun setRiverColor(color: Int) {
        mPaint.color = color
    }

    fun setRiverWidth(width: Float) {
        mPaint.strokeWidth = width
    }

    fun initRowing() = this::mRowingView.isInitialized

    fun move(progress: Float) {

        if (!this::mRowingView.isInitialized) {
            //小船
            mRowingView = RowingView(context)
            addView(mRowingView)
            mRowingView.layout(0, 0, 200, 200)
        }

        mPathMeasure.getPosTan(progress * mPathMeasure.length, mPos, mTan)

        postInvalidate()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {}

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(mPath, mPaint)
        if (this::mRowingView.isInitialized) {
            mRowingView.apply {
                translationX = mPos[0] - mRowingView.width / 2
                translationY = mPos[1] - mRowingView.height / 2
                rotation = (atan2(mTan[1], mTan[0]) * 180 / PI + 180).toFloat()
            }
        }
    }
}