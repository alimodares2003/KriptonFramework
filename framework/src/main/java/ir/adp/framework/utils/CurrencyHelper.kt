package ir.adp.framework.utils

import android.content.Context
import ir.adp.framework.R
import java.text.DecimalFormat

/**
 * Created by Ali on 8/4/2019.
 */

object CurrencyHelper

fun Double.formatCurrency(context: Context): String {
    return DecimalFormat("#,###").format(toDouble()) + " " + context.getString(R.string.toman)
}

fun Int.formatCurrency(context: Context): String {
    return DecimalFormat("#,###").format(toInt()) + " " + context.getString(R.string.toman)
}