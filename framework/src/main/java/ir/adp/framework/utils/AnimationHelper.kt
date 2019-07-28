package ir.adp.framework.utils

import android.view.View

/**
 * Created by Ali on 7/17/2019.
 */

object AnimationHelper

fun View.hideAlphaAnimate() {
    this.animate()
        .alpha(0.0f)
        .setDuration(300).withEndAction {
            this.visibility = View.GONE
        }
}

fun View.showAlphaAnimate() {
    this.animate()
        .alpha(1.0f)
        .setDuration(300).withEndAction {
            this.visibility = View.VISIBLE
        }
}