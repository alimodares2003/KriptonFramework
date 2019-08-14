package ir.adp.framework.base

import android.content.Context
import io.reactivex.Observable
import ir.adp.framework.data.api.ApiClient
import ir.adp.framework.data.api.ApiManager
import ir.adp.framework.utils.listener.IApiListener
import ir.adp.framework.utils.listener.IIndexApiListener
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

    protected fun <T> run(context: Context, service: Observable<Response<List<T>>>, listener: IIndexApiListener) {
        ApiManager.runApi(context, service, listener = listener)
    }

    protected fun <T> run(context: Context, service: Observable<Response<T>>, listener: IApiListener) {
        ApiManager.runApi(context, service, listener)
    }
}