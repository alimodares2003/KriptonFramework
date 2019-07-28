package ir.adp.framework.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import ir.adp.framework.R


object IntentHelper

fun Context.goActivity(activity: Class<*>) {
    val intent = Intent(this, activity)
    startActivity(intent)
}

fun Fragment.goActivity(activity: Class<*>) {
    val intent = Intent(context, activity)
    startActivity(intent)
}

fun Fragment.goActivity(intent: Intent) {
    startActivity(intent)
}

fun Activity.goActivity(activity: Class<*>) {
    val intent = Intent(this, activity)
    startActivity(intent)
}

fun Activity.goActivity(intent: Intent) {
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
    overridePendingTransition(R.anim.slide_in,R.anim.slide_out)
}

//fun Context?.goChrome(url: String?) {
//    if (this == null || url == null)
//        return
//    avoidExceptionError({
//        val intentBuilder = CustomTabsIntent.Builder()
//        intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.primaryColor))
//        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(this,R.color.secondaryColor))
//        intentBuilder.build().launchUrl(this, Uri.parse(url))
//    }) {
//        goBrowser(url)
//    }
//}

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