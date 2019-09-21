package ir.adp.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.vlonjatg.progressactivity.ProgressFrameLayout
import ir.adp.framework.R
import ir.adp.framework.utils.DisplayHelper
import ir.adp.framework.utils.changeColorDrawableRes


/**
 * Created by Ali on 6/19/2019.
 */

class ErrorView : ProgressFrameLayout {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    override fun showEmpty(icon: Int, title: String?, description: String?) {
        val res = changeColorDrawableRes(
            context,
            icon,
            ContextCompat.getColor(context, R.color.textSecondary_default)
        )
        super.showEmpty(res, title, description)
        overrideFonts(this)
        overrideButtonStyle(this)
    }

    override fun showError(
        icon: Int,
        title: String?,
        description: String?,
        buttonText: String?,
        buttonClickListener: OnClickListener?
    ) {
        val res = changeColorDrawableRes(
            context,
            icon,
            ContextCompat.getColor(context, R.color.textSecondary_default)
        )
        super.showError(res, title, description, buttonText, buttonClickListener)
        overrideFonts(this)
        overrideButtonStyle(this)
    }

    private fun initView() {
        overrideFonts(this)
        overrideButtonStyle(this)
    }

    private fun overrideFonts(v: View) {
        DisplayHelper.init(context)
        try {
            if (v is ViewGroup) {
                for (i in 0 until v.childCount) {
                    val child = v.getChildAt(i)
                    overrideFonts(child)
                }
            } else if (v is TextView) {
                if (v.text == "") {
                    v.visibility = View.GONE
                } else {
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, DisplayHelper.i(4), 0, 0)
                    val typeface = Typeface.createFromAsset(
                        context.assets,
                        context.getString(R.string.font_mainMedium)
                    )
                    v.typeface = typeface
                    v.layoutParams = params
                }
            }
        } catch (e: Exception) {
        }
    }

    @SuppressLint("ResourceType")
    private fun overrideButtonStyle(v: View) {
        try {
            if (v is ViewGroup) {
                for (i in 0 until v.childCount) {
                    val child = v.getChildAt(i)
                    overrideButtonStyle(child)
                }
            } else if (v is MaterialButton) {
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, 0)
                v.layoutParams = params
                v.setBackgroundColor(Color.TRANSPARENT)
                v.rippleColor = ContextCompat.getColorStateList(context, R.color.ripple)
                val typeface = Typeface.createFromAsset(
                    context.assets,
                    context.getString(R.string.font_mainMedium)
                )
                v.typeface = typeface
                v.setPadding(0, 0, 0, 0)
            }
        } catch (e: Exception) {
        }
    }

}