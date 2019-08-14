package ir.adp.framework.data.api

import android.annotation.SuppressLint
import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.adp.framework.R
import ir.adp.framework.utils.avoidException
import ir.adp.framework.utils.isNetworkAvailable
import ir.adp.framework.utils.listener.IApiListener
import ir.adp.framework.utils.listener.IIndexApiListener
import ir.adp.framework.utils.toastL
import retrofit2.Response

object ApiManager {
    @SuppressLint("CheckResult")
    fun <T> runApi(context: Context, service: Observable<Response<T>>, listener: IApiListener) {
        avoidException {
            if (isNetworkAvailable(context)) {
                listener.onPreApi()
                service.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        if (response.isSuccessful) {
                            listener.onSuccessApi(response, listener)
                        } else {
                            context.toastL(response.code().toString() + context.getString(R.string.errorCode))
                        }
                    }, {
                        listener.onFailureApi(context)
                    })
            } else {
                context.toastL(context.getString(R.string.noInternetPremision))
            }
        }
    }

    @SuppressLint("CheckResult")
    fun <T> runApi(
        context: Context,
        service: Observable<Response<List<T>>>,
        listener: IIndexApiListener
    ) {
        avoidException {
            if (isNetworkAvailable(context)) {
                service.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        if (response.isSuccessful) {
                            listener.onSuccessApi(response, listener)
                        } else {
                            listener.onFailureServer(context, response.code().toString())
                        }
                    }, {
                        listener.onFailureApi(context, it.localizedMessage.toString())
                    })
            } else {
                listener.onErrorInternet(context)
            }
        }
    }

}