package ir.adp.framework.utils

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.adp.framework.R
import java.text.DecimalFormat

object StringHelper {
    fun setFontToText(context: Context, text: String): String {
        val font = Typeface.createFromAsset(context.assets, context.getString(R.string.font_mainRegular))
        val mNewTitle = SpannableString(text)
        mNewTitle.setSpan(CustomTypefaceSpan("", font), 0, mNewTitle.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return mNewTitle.toString()
    }
}

inline fun <reified T> String?.parseToClassGSON(): T? {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}

fun Any?.parseClassToStringGSON(): String? {
    return Gson().toJson(this)
}

fun Double.formatCurrency(context: Context): String {
    return DecimalFormat("#,###").format(toDouble()) + " " + context.getString(R.string.toman)
}

fun Int.formatCurrency(context: Context): String {
    return DecimalFormat("#,###").format(toInt()) + " " + context.getString(R.string.toman)
}