package ir.adp.framework.utils

import android.content.Context
import android.net.ConnectivityManager
import ir.adp.framework.utils.avoidException
import java.net.HttpURLConnection
import java.net.URL


object InternetHelper

fun isConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected) {
        avoidException {
            val url = URL("http://www.google.com/")
            val urlc = url.openConnection() as HttpURLConnection
            urlc.setRequestProperty("User-Agent", "test")
            urlc.setRequestProperty("Connection", "close")
            urlc.connectTimeout = 1000 // mTimeout is in seconds
            urlc.connect()
            return urlc.responseCode == 200
        }
    }
    return false
}

fun isNetworkAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null
}
