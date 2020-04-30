package com.ffeiyue.rowingview

import android.content.Context
import android.graphics.Path
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.tan

fun main() {
    println(tan(PI / 4))
}

fun Context.dp2px(dp: Float) = dp * resources.displayMetrics.density + 0.5f

fun getPath(pos: Int, width: Float, height: Float): Path {
    val radius = 100f
    val firstHeight = height / 2
    val firstWidth: Float
    val lastWidth: Float
    if (pos % 2 == 0) {
        firstWidth = width / 8
        lastWidth = width / 8 * 7
    } else {
        firstWidth = width / 8 * 7
        lastWidth = width / 8
    }

    return Path().apply {
        moveTo(firstWidth, 0f)
        //添加直线
        rLineTo(0f, firstHeight)

        if (pos % 2 == 0) {
            //添加圆弧
            arcTo(firstWidth,
                    firstHeight - radius,
                    firstWidth + 2 * radius,
                    firstHeight + radius,
                    180f,
                    -90f,
                    false)

            rLineTo(lastWidth - firstWidth - 2 * radius, 0f)

            arcTo(lastWidth - 2 * radius,
                    firstHeight + radius,
                    lastWidth,
                    firstHeight + 3 * radius,
                    -90f,
                    90f,
                    false)
        } else {
            arcTo(firstWidth - 2 * radius,
                    firstHeight - radius,
                    firstWidth,
                    firstHeight + radius,
                    0f,
                    90f,
                    false)

            rLineTo(lastWidth - firstWidth + 2 * radius, 0f)

            arcTo(lastWidth,
                    firstHeight + radius,
                    lastWidth + 2 * radius,
                    firstHeight + 3 * radius,
                    -90f,
                    -90f,
                    false)
        }

        rLineTo(0f, firstHeight - 2 * radius)
    }
}