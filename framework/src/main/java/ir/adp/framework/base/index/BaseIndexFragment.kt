package ir.adp.framework.base.index

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.adp.framework.R
import ir.adp.framework.base.BaseFragment
import ir.adp.framework.utils.*
import ir.adp.framework.utils.listener.IIndexApiListener
import ir.adp.framework.utils.model.ErrorViewModel
import ir.adp.widgets.ErrorView
import retrofit2.Response


open class BaseIndexFragment<T> : BaseFragment(), IIndexApiListener {

    var rv_index: RecyclerView? = null
    var srl_index: SwipeRefreshLayout? = null
    var errorView_index: ErrorView? = null
    var services: Observable<Response<List<T>>>? = null

    lateinit var list: ArrayList<T>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(layout, container, false)
        srl_index = v.findViewById(R.id.srl)
        rv_index = v.findViewById(R.id.rv)
        errorView_index = v.findViewById(R.id.ev)

        list = ArrayList()

        srl_index?.setColorSchemeResources(R.color.colorPrimary)
        srl_index?.setOnRefreshListener {
            runApi(context!!, services!!, this)
        }
        return v
    }

    fun callApiIndex(srv: Observable<Response<List<T>>>) {
        services = srv
        errorView_index?.showLoading()
        runApi(context!!, services!!, this)
    }


    @Suppress("UNCHECKED_CAST")
    override fun onSuccessApi(rs: Response<*>, listener: IIndexApiListener) {
        list.clear()
        list.addAll(rs.body() as ArrayList<T>)

        if (list.isEmpty()) {
            errorView_index?.showEmpty(context!!, listener.onEmptyResponse())
            return
        }

        rv_index?.adapter?.notifyDataSetChanged()
        errorView_index?.showContent()
    }

    override fun onEmptyResponse(): ErrorViewModel {
        return ErrorType.ERROR_EMPTY_DEFAULT
    }

    @SuppressLint("CheckResult")
    fun runApi(context: Context, service: Observable<Response<List<T>>>, listener: IIndexApiListener) {
        avoidException {
            srl_index?.isRefreshing = false
            if (isNetworkAvailable(context)) {
                service.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ response ->
                            if (response.isSuccessful) {
                                listener.onSuccessApi(response, listener)
                            } else {
                                toastL(response.code().toString() + context.getString(R.string.errorCode))
                            }
                        }, {
                            //                            toastL(it.localizedMessage)
                            listener.onFailureApi(context, errorView_index!!) {
                                errorView_index?.showLoading()
                                runApi(context, services!!, this)
                            }
                        })

            } else {
                listener.onErrorInternet(context, errorView_index!!) {
                    errorView_index?.showLoading()
                    runApi(context, services!!, this)
                }
            }
        }
    }
}