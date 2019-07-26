package ir.adp.framework.base.index

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.adp.framework.R
import ir.adp.framework.base.BaseActivity
import ir.adp.framework.utils.*
import ir.adp.framework.utils.listener.IIndexApiListener
import ir.adp.framework.utils.model.ErrorViewModel
import ir.adp.widgets.ErrorView
import retrofit2.Response


@SuppressLint("Registered")
open class BaseIndexActivity<T> : BaseActivity(),
    IIndexApiListener {

    var rv_index: RecyclerView? = null
    var srl_index: SwipeRefreshLayout? = null
    var errorView_index: ErrorView? = null
    var services: Observable<Response<List<T>>>? = null
    var hasToolbarElevationOnScrollListener: Boolean = false

    lateinit var list: ArrayList<T>

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        srl_index = findViewById(R.id.srl)
        rv_index = findViewById(R.id.rv)
        errorView_index = findViewById(R.id.ev)

        list = ArrayList()

        srl_index?.setColorSchemeResources(R.color.colorPrimary)
        srl_index?.setOnRefreshListener {
            runApi(this, services!!, this)
        }


        if (hasToolbarElevationOnScrollListener) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar.elevation = 0f
            }

            val initialTopPosition = rv_index?.top
            rv_index?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (rv_index?.getChildAt(0)?.top!! < initialTopPosition!!) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar.elevation = 50f
                        }
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar.elevation = 0f
                        }
                    }
                }
            })
        }

    }

    fun callApiIndex(srv: Observable<Response<List<T>>>) {
        services = srv
        errorView_index?.showLoading()
        runApi(this, services!!, this)
    }

    override fun onEmptyResponse(): ErrorViewModel {
        return ErrorType.ERROR_EMPTY_DEFAULT
    }


    override fun onSuccessApi(rs: Response<*>, listener: IIndexApiListener) {
        list.clear()

        list.addAll(rs.body() as ArrayList<T>)

        if (list.isEmpty()) {
            errorView_index?.showEmpty(this, listener.onEmptyResponse())
            return
        }

        rv_index?.adapter?.notifyDataSetChanged()
        errorView_index?.showContent()
    }

    @SuppressLint("CheckResult")
    fun runApi(context: Context, service: Observable<Response<List<T>>>, listener: IIndexApiListener) {
        srl_index?.isRefreshing = false
        avoidException {
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
                                runApi(this, services!!, this)
                            }
                        })

            } else {
                listener.onErrorInternet(context, errorView_index!!) {
                    errorView_index?.showLoading()
                    runApi(this, services!!, this)
                }
            }
        }
    }
}
