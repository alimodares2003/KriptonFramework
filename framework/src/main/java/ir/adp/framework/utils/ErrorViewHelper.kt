package ir.adp.framework.utils

import android.content.Context
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import ir.adp.framework.R
import ir.adp.framework.utils.model.ErrorViewModel
import ir.adp.widgets.ErrorView

object ErrorViewHelper

    fun showErrorApi(context: Context, errorView: ErrorView, listener: () -> Unit) {
        errorView.alpha = 0f
        val anim = errorView.animate()?.alpha(1f)?.withLayer()
        anim?.duration = 600
        anim?.interpolator = FastOutSlowInInterpolator()
        anim?.start()
        errorView.showError(
            R.drawable.ic_warning,
            context.getString(R.string.errorInServerConnect),
            context.getString(R.string.pleaseTryAgain),
            context.getString(R.string.tryAgain)
        ) {
            listener()
        }
    }

    fun showErrorInternet(context: Context, errorView: ErrorView, listener: () -> Unit) {
        errorView.alpha = 0f
        val anim = errorView.animate()?.alpha(1f)?.withLayer()
        anim?.duration = 600
        anim?.interpolator = FastOutSlowInInterpolator()
        anim?.start()
        errorView.showError(
            R.drawable.ic_no_internet,
            StringHelper.setFontToText(context, context.getString(R.string.noInternet)),
            StringHelper.setFontToText(
                context,
                context.getString(R.string.checkYourInternet)
            ),
            StringHelper.setFontToText(context, context.getString(R.string.tryAgain))
        ) {
            listener()
        }
    }

    fun ErrorView.showEmpty(context: Context, model: ErrorViewModel) {
        showEmpty(model.drawable, context.getString(model.titleRes), context.getString(model.descriptionRes))
    }
