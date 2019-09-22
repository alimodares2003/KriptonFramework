package ir.adp.framework.base.index

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.Observable
import ir.adp.framework.R
import ir.adp.framework.base.BaseActivity
import ir.adp.framework.utils.ErrorType
import ir.adp.framework.utils.listener.IIndexApiListener
import ir.adp.framework.utils.model.ErrorViewModel
import ir.adp.framework.utils.showEmpty
import ir.adp.framework.utils.showErrorApi
import ir.adp.framework.utils.showErrorInternet
import ir.adp.widgets.ErrorView
import retrofit2.Response


@SuppressLint("Registered")
open class BaseIndexActivity<T> : BaseActivity(), IIndexApiListener {

    protected var rv_index: RecyclerView? = null
    protected var srl_index: SwipeRefreshLayout? = null
    protected var errorView_index: ErrorView? = null
    protected var services: Observable<Response<List<T>>>? = null
    protected var hasToolbarElevationOnScrollListener: Boolean = false
    private var mainPresenter = BaseIndexPresenter<IIndexApiListener>()

    protected lateinit var list: ArrayList<T>

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        mainPresenter.onAttach(this)
        srl_index = findViewById(R.id.srl)
        rv_index = findViewById(R.id.rv)
        errorView_index = findViewById(R.id.ev)

        list = ArrayList()

        srl_index?.setColorSchemeResources(R.color.onPrimaryColor)
        srl_index?.setOnRefreshListener {
            mainPresenter.run(this, services!!, this)
        }


        if (hasToolbarElevationOnScrollListener) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar?.elevation = 0f
            }

            val initialTopPosition = rv_index?.top
            rv_index?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (rv_index?.getChildAt(0)?.top!! < initialTopPosition!!) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar?.elevation = 50f
                        }
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar?.elevation = 0f
                        }
                    }
                }
            })
        }

    }

    fun callApiIndex(srv: Observable<Response<List<T>>>) {
        services = srv
        errorView_index?.showLoading()
        mainPresenter.run(this, services!!, this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onSuccessApi(rs: Response<*>, listener: IIndexApiListener) {
        list.clear()
        list.addAll(rs.body() as ArrayList<T>)

        if (list.isEmpty()) {
            errorView_index?.showEmpty(this, listener.onEmptyResponse())
            return
        }

        rv_index?.adapter?.notifyDataSetChanged()
        errorView_index?.showContent()
        srl_index?.isRefreshing = false
    }

    override fun onEmptyResponse(): ErrorViewModel {
        return ErrorType.ERROR_EMPTY_DEFAULT
    }

    override fun onFailureApi(context: Context, errorText: String) {
        showErrorApi(context, errorView_index!!) {
            errorView_index?.showLoading()
            srl_index?.isRefreshing = false
            mainPresenter.run(context, services!!, this)
        }
    }

    override fun onFailureServer(context: Context, errorText: String) {
        errorView_index?.showError(
            R.drawable.ic_warning,
            getString(R.string.error),
            errorText + context.getString(R.string.errorCode),
            getString(R.string.tryAgain)
        ) {
            errorView_index?.showLoading()
            srl_index?.isRefreshing = false
            mainPresenter.run(context, services!!, this)
        }
    }

    override fun onErrorInternet(context: Context) {
        showErrorInternet(context, errorView_index!!) {
            errorView_index?.showLoading()
            srl_index?.isRefreshing = false
            mainPresenter.run(context, services!!, this)
        }
    }

    override fun onResume() {
        super.onResume()
        errorView_index?.showLoading()
        if (services != null)
            mainPresenter.run(this, services!!, this)
    }
}
