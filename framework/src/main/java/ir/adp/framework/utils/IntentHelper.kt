package ir.adp.framework.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ir.adp.framework.R


object IntentHelper

fun Fragment.goActivity(activity: Class<*>) {
    val intent = Intent(context, activity)
    startActivity(intent)
}

fun Fragment.goActivity(intent: Intent) {
    startActivity(intent)
}

fun Fragment.goActivity(activity: Class<*>, names: Array<String>, vararg data: Any) {
    val intent = Intent(context, activity)
    for (j in 0 until data.size) {
        val item = data[j]
        var name = ""
        for (i in 0 until names.size) {
            name = names[j]
        }
        when (item) {
            is Boolean -> intent.putExtra(name, item)
            is Int -> intent.putExtra(name, item)
            is Long -> intent.putExtra(name, item)
            is Float -> intent.putExtra(name, item)
            is String -> intent.putExtra(name, item)
            is Double -> intent.putExtra(name, item)
            is Short -> intent.putExtra(name, item)
            is Parcelable -> intent.putExtra(name, item)
            is Array<*> -> intent.putExtra(name, item)
            is ArrayList<*> -> intent.putExtra(name, item)
            is DoubleArray -> intent.putExtra(name, item)
            is IntArray -> intent.putExtra(name, item)
            is CharArray -> intent.putExtra(name, item)
            is FloatArray -> intent.putExtra(name, item)
            is ShortArray -> intent.putExtra(name, item)
            is LongArray -> intent.putExtra(name, item)
            is Bundle -> intent.putExtra(name, item)
        }
    }
    startActivity(intent)
}

fun Fragment.goActivity(activity: Class<*>, model: Parcelable, name: String = "model") {
    val intent = Intent(context, activity)
    intent.putExtra(name, model)
    startActivity(intent)
}

fun Activity.goActivity(activity: Class<*>, isFinish: Boolean = false) {
    if (isFinish)
        finish()
    val intent = Intent(this, activity)
    startActivity(intent)
}

fun Activity.goActivity(intent: Intent, isFinish: Boolean = false) {
    if (isFinish)
        finish()
    startActivity(intent)
}

fun Activity.goActivity(
    activity: Class<*>,
    names: Array<String>,
    vararg data: Any,
    isFinish: Boolean = false
) {
    if (isFinish)
        finish()
    val intent = Intent(this, activity)
    for (j in 0 until data.size) {
        val item = data[j]
        var name = ""
        for (i in 0 until names.size) {
            name = names[j]
        }
        when (item) {
            is Boolean -> intent.putExtra(name, item)
            is Int -> intent.putExtra(name, item)
            is Long -> intent.putExtra(name, item)
            is Float -> intent.putExtra(name, item)
            is String -> intent.putExtra(name, item)
            is Double -> intent.putExtra(name, item)
            is Short -> intent.putExtra(name, item)
            is Parcelable -> intent.putExtra(name, item)
            is Array<*> -> intent.putExtra(name, item)
            is ArrayList<*> -> intent.putExtra(name, item)
            is DoubleArray -> intent.putExtra(name, item)
            is IntArray -> intent.putExtra(name, item)
            is CharArray -> intent.putExtra(name, item)
            is FloatArray -> intent.putExtra(name, item)
            is ShortArray -> intent.putExtra(name, item)
            is LongArray -> intent.putExtra(name, item)
            is Bundle -> intent.putExtra(name, item)
        }
    }
    startActivity(intent)
}

fun Activity.goActivity(
    activity: Class<*>,
    model: Parcelable,
    name: String = "model",
    isFinish: Boolean = false
) {
    if (isFinish)
        finish()
    val intent = Intent(this, activity)
    intent.putExtra(name, model)
    startActivity(intent)
}

fun Context?.goBrowser(url: String?) {
    if (this == null || url == null)
        return
    avoidException {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(i)
    }
}

fun Activity.showAnimation() {
    overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
}

fun Context?.goChrome(url: String?) {
    if (this == null || url == null)
        return
    avoidExceptionError({
        val intentBuilder = CustomTabsIntent.Builder()
        intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        intentBuilder.build().launchUrl(this, Uri.parse(url))
    }) {
        goBrowser(url)
    }
}

fun Fragment?.goChrome(url: String) {
    if (this == null)
        return
    context.goChrome(url)
}

fun Context?.goInstagramPage(pageName: String?) {
    if (this == null)
        return
    avoidExceptionError({
        val uri = Uri.parse("http://instagram.com/_u/$pageName")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.instagram.android")
        startActivity(intent)
    }) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/$pageName")))
    }
}

fun Context?.goTelegramPage(pageName: String?) {
    if (this == null || pageName == null)
        return
    avoidException {
        val uri = Uri.parse("https://telegram.me/$pageName")
        val telegram = Intent(Intent.ACTION_VIEW, uri)
        startActivity(telegram)
    }
}

fun Activity.restartApplication(context: Context) {
    val i = context.packageManager.getLaunchIntentForPackage(context.packageName)!!
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(i)
}

fun Fragment.restartApplication(context: Context) {
    val i = context.packageManager.getLaunchIntentForPackage(context.packageName)!!
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(i)
}

fun Context.restartApplication() {
    val i = this.packageManager.getLaunchIntentForPackage(this.packageName)!!
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(i)
}