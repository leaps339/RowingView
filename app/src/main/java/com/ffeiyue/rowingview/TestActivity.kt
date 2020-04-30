package com.ffeiyue.rowingview

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val width = resources.displayMetrics.widthPixels.toFloat() - dp2px(32f)
        val height = mCardView.layoutParams.height.toFloat()
        Log.d(TestActivity::class.simpleName, "width:$width,height:$height")
        mRiverView.apply {
            setBackgroundColor(Color.parseColor("#4bab64"))
            setRiverColor(Color.parseColor("#5ec87b"))
            setRiverWidth(100f)
            setPath(getPath(0, width, height))
        }

        moveUp.setOnClickListener { MainActivity.start(this) }
        moveDown.setOnClickListener { mRiverView.move(0.01f) }
    }
}
