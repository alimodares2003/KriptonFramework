package ir.adp.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import ir.adp.framework.R

/**
 * Created by Ali on 7/31/2019.
 */

class ImageView : AppCompatImageView {

    var first = 0f
    var second = 0f
    var isRatio = false

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context) : super(context)

    @SuppressLint("Recycle")
    private fun initView(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageView)
        val ratio = typedArray.getString(R.styleable.ImageView_aspectRatio)
        isRatio = typedArray.getBoolean(R.styleable.ImageView_aspectRatioEnabled, false)
        if (!ratio.isNullOrEmpty()) {
            second = ratio.substring(ratio.lastIndexOf(":") + 1).toFloat()
            first = ratio.replace(":$width", "").toFloat()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (isRatio) {
            val widthSize = MeasureSpec.getSize(widthMeasureSpec)
            val heightSize = (second / first * widthSize).toInt()
            val newHeightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY)
            super.onMeasure(widthMeasureSpec, newHeightSpec)
        } else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}