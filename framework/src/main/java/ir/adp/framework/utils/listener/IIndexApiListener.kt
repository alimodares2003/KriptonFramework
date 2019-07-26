package ir.adp.framework.utils.listener

import android.content.Context
import ir.adp.framework.utils.model.ErrorViewModel
import ir.adp.framework.utils.showErrorApi
import ir.adp.framework.utils.showErrorInternet
import ir.adp.widgets.ErrorView
import retrofit2.Response

interface IIndexApiListener {
    fun onSuccessApi(rs: Response<*>, listener: IIndexApiListener)
    fun onFailureApi(context: Context, errorView: ErrorView, listener: () -> Unit) {
        showErrorApi(context, errorView) {
            listener()
        }
    }

    fun onErrorInternet(context: Context, errorView: ErrorView, listener: () -> Unit) {
        showErrorInternet(context, errorView) {
            listener()
        }
    }

    fun onEmptyResponse(): ErrorViewModel
}