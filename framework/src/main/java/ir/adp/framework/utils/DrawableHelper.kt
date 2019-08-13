package ir.adp.framework.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
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

fun changeColorDrawableVector(c: Context?, direction: String, resDrawable: Int, color: Int): Drawable? {
    if (c == null)
        return null

    val d = VectorDrawableCompat.create(c.resources, resDrawable, null) ?: return null
    d.mutate()
    if (color != -2)
        d.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    if (Build.VERSION.SDK_INT > 23 && direction == "rtl" && d.isAutoMirrored) {
        return flipDrawable(d)
    }
    return d
}

private fun flipDrawable(d: Drawable): Drawable {
    val arD = arrayOf(d)
    return object : LayerDrawable(arD) {
        override fun draw(canvas: Canvas) {
            canvas.save()
            canvas.scale(-1f, 1f, (d.bounds.width() / 2).toFloat(), (d.bounds.width() / 2).toFloat())
            super.draw(canvas)
            canvas.restore()
        }
    }
}