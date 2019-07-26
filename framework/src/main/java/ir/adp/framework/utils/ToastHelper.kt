@file:Suppress("unused")

package ir.adp.framework.utils

import android.R.id
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ir.adp.framework.R


/**
 * Created by 1HE on 9/20/2018.
 */

object ToastHelper {

    var t: Toast? = null

    class TypefaceSpan(private val mTypeface: Typeface?) : MetricAffectingSpan() {

        override fun updateMeasureState(p: TextPaint) {
            p.typeface = mTypeface
            p.flags = p.flags or Paint.SUBPIXEL_TEXT_FLAG
        }

        override fun updateDrawState(tp: TextPaint) {
            tp.typeface = mTypeface
            tp.flags = tp.flags or Paint.SUBPIXEL_TEXT_FLAG
        }
    }
}

fun Context?.toastL(res: Int) {
    if (this == null)
        return
    toast(getString(res), Toast.LENGTH_LONG)
}

fun Context?.toastS(res: Int) {
    if (this == null)
        return
    toast(getString(res), Toast.LENGTH_SHORT)
}

fun Context?.toastL(message: String?) {
    if (this == null)
        return
    toast(message, Toast.LENGTH_LONG)
}

fun Context?.toastS(message: String?) {
    if (this == null)
        return
    toast(message, Toast.LENGTH_SHORT)
}

fun Fragment?.toastL(res: Int) {
    if (this == null)
        return
    context.toastL(res)
}

fun Fragment?.toastS(res: Int) {
    if (this == null)
        return
    context?.toastS(res)
}

fun Fragment?.toastL(message: String?) {
    if (this == null)
        return
    context.toastL(message)
}

fun Fragment?.toastS(message: String?) {
    if (this == null)
        return
    context.toastS(message)
}

private fun Context?.toast(message: String?, duration: Int) {
    if (this == null || message == null)
        return

    avoidException {
        ToastHelper.t?.cancel()

        val font = Typeface.createFromAsset(assets, getString(R.string.font_mainRegular))
        val span = SpannableString(message)
        span.setSpan(
            ToastHelper.TypefaceSpan(font),
                0,
                span.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        ToastHelper.t = Toast.makeText(this, span, duration)

        ToastHelper.t?.show()
    }
}

fun Activity?.snackBarS(message: String) {
    if (this == null)
        return
    snackbar(message, Snackbar.LENGTH_SHORT)
}

fun Activity?.snackBarS(res: Int) {
    if (this == null)
        return
    snackbar(getString(res), Snackbar.LENGTH_SHORT)
}

fun Activity?.snackBarL(message: String) {
    if (this == null)
        return
    snackbar(message, Snackbar.LENGTH_LONG)
}

fun Activity?.snackBarL(res: Int) {
    if (this == null)
        return

    snackbar(getString(res), Snackbar.LENGTH_LONG)
}

fun Activity?.snackBarActionL(message: String, actionText: String, listener: () -> Unit) {
    if (this == null)
        return

    snackbar(message, Snackbar.LENGTH_LONG, actionText, listener)
}

fun Activity?.snackBarActionL(res: Int, actionText: String, listener: () -> Unit) {
    if (this == null)
        return

    snackbar(getString(res), Snackbar.LENGTH_LONG, actionText, listener)
}

fun Activity?.snackBarActionS(message: String, actionText: String, listener: () -> Unit) {
    if (this == null)
        return

    snackbar(message, Snackbar.LENGTH_SHORT, actionText, listener)
}

fun Activity?.snackBarActionS(res: Int, actionText: String, listener: () -> Unit) {
    if (this == null)
        return

    snackbar(getString(res), Snackbar.LENGTH_SHORT, actionText, listener)
}

private fun Activity?.snackbar(message: String, duration: Int, actionText: String? = null, listener: () -> Unit? = {}) {
    if (this == null)
        return
    if (actionText.isNullOrEmpty()) {
        Snackbar.make(findViewById(id.content), message, duration).show()
    }

    Snackbar.make(findViewById(id.content), message, duration).setAction(actionText) { listener() }.show()
}


