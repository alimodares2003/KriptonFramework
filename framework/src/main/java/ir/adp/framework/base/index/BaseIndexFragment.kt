package ir.adp.framework.base.index

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.Observable
import ir.adp.framework.R
import ir.adp.framework.base.BaseFragment
import ir.adp.framework.utils.ErrorType
import ir.adp.framework.utils.listener.IIndexApiListener
import ir.adp.framework.utils.model.ErrorViewModel
import ir.adp.framework.utils.showEmpty
import ir.adp.framework.utils.showErrorApi
import ir.adp.framework.utils.showErrorInternet
import ir.adp.widgets.ErrorView
import retrofit2.Response


open class BaseIndexFragment<T> : BaseFragment(), IIndexApiListener {

    var rv_index: RecyclerView? = null
    var srl_index: SwipeRefreshLayout? = null
    var errorView_index: ErrorView? = null
    var services: Observable<Response<List<T>>>? = null
    var presenter = BaseIndexPresenter<IIndexApiListener>()

    lateinit var list: ArrayList<T>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(layout, container, false)
        presenter.onAttach(this)
        srl_index = v.findViewById(R.id.srl)
        rv_index = v.findViewById(R.id.rv)
        errorView_index = v.findViewById(R.id.ev)

        list = ArrayList()

        srl_index?.setColorSchemeResources(R.color.onPrimaryColor)
        srl_index?.setOnRefreshListener {
            presenter.run(context!!, services!!, this)
        }
        return v
    }

    fun callApiIndex(srv: Observable<Response<List<T>>>) {
        services = srv
        errorView_index?.showLoading()
        presenter.run(context!!, services!!, this)
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

    override fun onFailureApi(context: Context, errorText: String) {
        showErrorApi(context, errorView_index!!) {
            errorView_index?.showLoading()
            srl_index?.isRefreshing = false
            presenter.run(context, services!!, this)
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
            presenter.run(context, services!!, this)
        }
    }

    override fun onErrorInternet(context: Context) {
        showErrorInternet(context, errorView_index!!) {
            errorView_index?.showLoading()
            srl_index?.isRefreshing = false
            presenter.run(context, services!!, this)
        }
    }
}