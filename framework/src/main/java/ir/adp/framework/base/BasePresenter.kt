package ir.adp.framework.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import io.reactivex.Observable
import ir.adp.framework.R
import ir.adp.framework.base.index.BaseIndexActivity
import ir.adp.framework.base.index.BaseIndexFragment
import ir.adp.framework.data.api.ApiClient
import ir.adp.framework.data.api.ApiManager
import ir.adp.framework.utils.listener.IApiListener
import ir.adp.framework.utils.showLoading
import ir.adp.framework.utils.toastL
import retrofit2.Response

/**
 * Created by Ali on 8/12/2019.
 */

open class BasePresenter<V : BaseView> {

    @Suppress("MemberVisibilityCanBePrivate")
    lateinit var view: V

    fun onAttach(mvpView: V) {
        this.view = mvpView
    }

    inline fun <reified T> getService(): T {
        val retrofit = ApiClient()
        val cs: Class<T> = Class.forName(T::class.java.name) as Class<T>
        return retrofit.getClient().create(cs)
    }

    var dialog: MaterialDialog? = null
    fun <T> runApi(
        context: Context,
        api: Observable<Response<T>>,
        onPre: (context: Context) -> Unit = { dialog = showLoading(context, view.dialogText()).cancelable(false) },
        onComplete: () -> Unit = { dialog?.hide() },
        onSuccess: (rs: Response<*>) -> Unit,
        onFailure: (context: Context) -> Unit = {
            dialog?.hide()
            context.toastL(context.getString(R.string.errorInServerConnection))
        }
    ) {

        ApiManager.runApi(context, api, object : IApiListener {
            override fun onCompleteApi() {
                onComplete()
            }

            override fun onFailureApi(context: Context) {
                onFailure(context)
            }

            override fun onPreApi() {
                onPre(context)
            }

            override fun onSuccessApi(rs: Response<*>, listener: IApiListener) {
                onSuccess(rs)
            }
        })
    }

    fun <T> runApi(activity: Activity): BaseIndexActivity<T> {
        return activity as BaseIndexActivity<T>
    }

    fun <T> runApi(fragment: Fragment): BaseIndexFragment<T> {
        return fragment as BaseIndexFragment<T>
    }
}