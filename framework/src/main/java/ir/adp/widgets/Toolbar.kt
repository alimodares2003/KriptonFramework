package ir.adp.widgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import ir.adp.framework.R


/**
 * Created by Ali on 6/20/2019.
 */

class Toolbar : Toolbar {

    var textView = TextView(context)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    private fun initView() {
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        )
        textView.layoutParams = params
        title = textView.text
        val typeface = Typeface.createFromAsset(context.assets, context.getString(R.string.font_mainMedium))
        textView.typeface = typeface
        textView.textSize = 20f

        textView.setTextColor(ContextCompat.getColor(context, R.color.toolbar_color))
        addView(textView)
    }
}