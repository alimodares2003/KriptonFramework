package ir.adp.widgets

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import ir.adp.framework.R
import ir.adp.framework.utils.getRipple

/**
 * Created by Ali on 6/18/2019.
 */

class CardView : MaterialCardView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context) : super(context)

    override fun setForeground(foreground: Drawable?) {
        super.setForeground(getRipple(ContextCompat.getColor(context, R.color.ripple)))
    }
}