package ir.adp.framework.utils.listener

import android.content.Context
import ir.adp.framework.utils.model.ErrorViewModel
import retrofit2.Response

interface IIndexApiListener {
    fun onSuccessApi(rs: Response<*>, listener: IIndexApiListener)
    fun onFailureApi(context: Context, errorText: String)
    fun onFailureServer(context: Context, errorText: String)
    fun onErrorInternet(context: Context)
    fun onEmptyResponse(): ErrorViewModel
}