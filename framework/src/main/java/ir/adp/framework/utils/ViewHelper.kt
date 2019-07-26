package ir.adp.framework.utils

import android.view.View

open class ViewHelper

inline fun <T : View?> T.runAfterDelay(delay: Long, crossinline f: T.() -> Unit) {
    this?.postDelayed({
        avoidException { f() }
    }, delay)
}