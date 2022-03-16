package com.mgm.kiliaro.generals.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by Majid-Golmoradi on 3/16/2022.
 * Email: golmoradi.majid@gmail.com
 */
class CustomGridViewItem : FrameLayout {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        //float radius = 36.0f;
        @SuppressLint("DrawAllocation") val clipPath = Path()
        @SuppressLint("DrawAllocation") val rect = RectF(
            0F, 0F, this.width.toFloat(), this.height
                .toFloat()
        )
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW)
        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }

    companion object {
        var radius = 10.0f
    }
}