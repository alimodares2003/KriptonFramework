package ir.adp.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import ir.adp.framework.R

/**
 * Created by Ali on 6/3/2019.
 */

class TextView : TextView {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context) : super(context) {
//        initView()
    }

    @SuppressLint("CustomViewStyleable")
    private fun initView(attrs: AttributeSet) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ViewAttr, 0, 0)
        try {
            val fontPath = ta.getString(R.styleable.ViewAttr_fontPath)
            if (fontPath != null) {
                val typeface = Typeface.createFromAsset(context.assets, fontPath)
                setTypeface(typeface)
            } else {
                val typeface = Typeface.createFromAsset(context.assets, context.getString(R.string.font_mainRegular_faNum))
                setTypeface(typeface)
            }
        } finally {
            ta.recycle()
        }
    }
}