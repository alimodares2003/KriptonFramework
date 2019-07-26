package ir.adp.framework.utils.listener

import android.content.Context
import retrofit2.Response

interface IApiListener {
    fun onSuccessApi(rs: Response<*>, listener: IApiListener)
    fun onFailureApi(context: Context)
    fun onPreApi()
    fun onCompleteApi()
}