package ir.adp.framework.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import top.defaults.drawabletoolbox.DrawableBuilder


object DrawableHelper

fun getRipple(color: Int): Drawable {
    return DrawableBuilder()
            .rectangle()
            .ripple()
            .rippleColor(color)
            .build()
}

fun changeColorDrawableRes(c: Context?, resDrawable: Int, color: Int): Drawable? {
    if (c == null)
        return null

    val d = ContextCompat.getDrawable(c, resDrawable) ?: return null
    d.mutate()
    if (color != -2)
        d.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    return d
}

fun changeColorDrawable(c: Context?, d: Drawable, color: Int): Drawable? {
    if (c == null)
        return null

    d.mutate()
    if (color != -2)
        d.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    return d
}