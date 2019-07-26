package ir.adp.framework.base

import android.annotation.SuppressLint
import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.adp.framework.R
import ir.adp.framework.utils.avoidException
import ir.adp.framework.utils.isNetworkAvailable
import ir.adp.framework.utils.listener.IApiListener
import ir.adp.framework.utils.showLoading
import ir.adp.framework.utils.toastL
import retrofit2.Response

open class BaseFormFragment : BaseFragment(), IApiListener {

    var dialog: MaterialDialog? = null
    var dialogText: String = ""

    override fun onSuccessApi(rs: Response<*>, listener: IApiListener) {}

    override fun onPreApi() {
        dialog = showLoading(context!!, dialogText).cancelable(false)
    }

    override fun onCompleteApi() {
        dialog?.hide()
    }

    override fun onFailureApi(context: Context) {
        toastL(context.getString(R.string.errorInServerConnection))
    }

    @SuppressLint("CheckResult")
    fun <T> runApi(context: Context, service: Observable<Response<T>>, listener: IApiListener) {
        avoidException {
            if (isNetworkAvailable(context)) {
                listener.onPreApi()
                service.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ response ->
                            if (response.isSuccessful) {
                                listener.onCompleteApi()
                                listener.onSuccessApi(response, listener)
                            } else {
                                toastL(response.code().toString() + context.getString(R.string.errorCode))
                            }
                        }, {
                            listener.onFailureApi(context)
                        })
            } else {
                context.toastL(context.getString(R.string.noInternetPremision))
            }
        }
    }

}