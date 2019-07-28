package ir.adp.club.utils.listener

import android.content.Context
import retrofit2.Response

interface IFormApiListener {
    fun onSuccessApi(rs: Response<*>, listener: IFormApiListener)
    fun onFailureApi(context: Context)
    fun onPreApi()
    fun onCompleteApi()
}