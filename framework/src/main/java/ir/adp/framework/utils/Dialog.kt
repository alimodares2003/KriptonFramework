package ir.adp.framework.utils

import android.content.Context
import android.graphics.Color
import android.widget.ProgressBar
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import ir.adp.framework.R
import ir.adp.widgets.TextView

object Dialog

fun showLoading(context: Context, text: String): MaterialDialog {
    var width = 0
    var height = 0
    val loading = MaterialDialog(context).show {
        customView(R.layout.loading_dialog)
        val attr = getCustomView()
        val tv = attr.findViewById<TextView>(R.id.tv_loading)
        val pb = attr.findViewById<ProgressBar>(R.id.pb)
        width = tv.length()
        height = pb.height
        tv.text = text
        tv.setTextColor(Color.BLACK)

    }.cornerRadius(8f)
    loading.window?.setLayout(550, 200)
    return loading
}

fun showAlert(
    context: Context,
    title: String,
    body: String,
    positiveText: String,
    negativeText: String,
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null,
    isCancellable: Boolean? = null
): MaterialDialog {
    val alert = MaterialDialog(context).show {
        title(text = title)
        message(text = body)
        positiveButton(text = positiveText) {
            if (onPositiveClick != null)
                onPositiveClick()
        }
        negativeButton(text = negativeText) {
            if (onNegativeClick != null)
                onNegativeClick()
        }
        if (isCancellable == null)
            cancelable(true)
        else
            cancelable(isCancellable)
    }
    return alert
}