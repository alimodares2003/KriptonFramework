package ir.adp.framework.base

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import io.reactivex.Observable
import ir.adp.framework.R
import ir.adp.framework.base.index.BaseFormPresenter
import ir.adp.framework.utils.listener.IApiListener
import ir.adp.framework.utils.showLoading
import ir.adp.framework.utils.toastL
import retrofit2.Response

open class BaseFormActivity : BaseActivity(), IApiListener {

    var dialog: MaterialDialog? = null
    var dialogText: String = ""
    private val mainPresenter = BaseFormPresenter<IApiListener>()

    override fun onSuccessApi(rs: Response<*>, listener: IApiListener) {}

    override fun onPreApi() {
        dialog = showLoading(this, dialogText).cancelable(false)
    }

    override fun onCompleteApi() {
        dialog?.hide()
    }

    override fun onFailureApi(context: Context) {
        dialog?.hide()
        toastL(context.getString(R.string.errorInServerConnection))

    }

    fun <T> callApi(service: Observable<Response<T>>) {
        mainPresenter.run(this, service, this)
    }

}