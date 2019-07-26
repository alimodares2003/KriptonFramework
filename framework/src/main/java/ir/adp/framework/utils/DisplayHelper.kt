@file:Suppress("unused")

package ir.adp.framework.utils

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import java.util.*

/**
 * Created by 1HE on 9/15/2018.
 */

object DisplayHelper {

    var dp: Float = 0f

    fun init(context: Context) {
        dp = context.resources.displayMetrics.density
    }

    fun f(f: Float) = f * dp

    fun f(i: Int) = i * dp

    fun i(i: Int) = (i * dp).toInt()

    fun i(f: Float) = (f * dp).toInt()

    fun toDp(pixelSize: Float) = pixelSize / dp

    fun getActionBarHeight(context: Context): Int {
        val tv = TypedValue()
        return if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        } else 0
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getDisplaySize(context: Context?): Point {
        return avoidExceptionReturn({
            val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val p = Point()

            val realMetrics = DisplayMetrics()
            display.getRealMetrics(realMetrics)
            p.x = realMetrics.widthPixels
            p.y = realMetrics.heightPixels

            p
        }, { Point() })
    }

    fun changeAppDirection(context: Context, direction: String) {
        val configuration = context.resources.configuration
        if (direction == "rtl") {
            configuration.setLayoutDirection(Locale("fa"))
        } else {
            configuration.setLayoutDirection(Locale.ENGLISH)
        }
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }


}