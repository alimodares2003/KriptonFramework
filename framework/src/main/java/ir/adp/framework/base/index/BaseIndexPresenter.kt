package ir.adp.framework.base.index

import android.content.Context
import io.reactivex.Observable
import ir.adp.framework.data.api.ApiManager
import ir.adp.framework.utils.listener.IIndexApiListener
import retrofit2.Response

/**
 * Created by Ali on 8/13/2019.
 */

open class BaseIndexPresenter<V : IIndexApiListener> {

    @Suppress("MemberVisibilityCanBePrivate")
    protected lateinit var view: V

    fun onAttach(mvpView: V) {
        this.view = mvpView
    }

    fun <T> run(context: Context, service: Observable<Response<List<T>>>, listener: IIndexApiListener) {
        ApiManager.runApi(context, service, listener)
    }
}