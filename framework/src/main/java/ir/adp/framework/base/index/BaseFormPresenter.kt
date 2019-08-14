package ir.adp.framework.base.index

import android.content.Context
import io.reactivex.Observable
import ir.adp.framework.data.api.ApiManager
import ir.adp.framework.utils.listener.IApiListener
import retrofit2.Response

/**
 * Created by Ali on 8/12/2019.
 */

open class BaseFormPresenter<V : IApiListener> {

    @Suppress("MemberVisibilityCanBePrivate")
    lateinit var view: V

    fun onAttach(mvpView: V) {
        this.view = mvpView
    }

    fun <T> run(context: Context, service: Observable<Response<T>>, listener: IApiListener) {
        ApiManager.runApi(context, service, listener)
    }
}