package ir.adp.widgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vlonjatg.progressactivity.ProgressFrameLayout
import ir.adp.framework.R


/**
 * Created by Ali on 6/19/2019.
 */

class ErrorView : ProgressFrameLayout {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    override fun showEmpty(icon: Int, title: String?, description: String?) {
        super.showEmpty(icon, title, description)
        overrideFonts(this)
    }

    override fun showError(
        icon: Int,
        title: String?,
        description: String?,
        buttonText: String?,
        buttonClickListener: OnClickListener?
    ) {
        super.showError(icon, title, description, buttonText, buttonClickListener)
        overrideFonts(this)
    }

    private fun initView() {
        overrideFonts(this)
    }

    private fun overrideFonts(v: View) {
        try {
            if (v is ViewGroup) {
                for (i in 0 until v.childCount) {
                    val child = v.getChildAt(i)
                    overrideFonts(child)
                }
            } else if (v is TextView) {
                val typeface = Typeface.createFromAsset(context.assets, context.getString(R.string.font_mainMedium))
                v.typeface = typeface
            }
        } catch (e: Exception) {
        }
    }

}