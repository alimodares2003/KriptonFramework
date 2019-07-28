package ir.adp.kriptonframework

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import ir.adp.framework.base.index.BaseIndexFragment
import ir.adp.framework.utils.ErrorType
import ir.adp.framework.utils.decoration.MaterialCardDecoration
import ir.adp.framework.utils.model.ErrorViewModel

class MainIndexFragment : BaseIndexFragment<MainResponse>() {

    init {
        layout = R.layout.fragment_main_index
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(context, 2)
        rv_index?.layoutManager = layoutManager
        rv_index?.addItemDecoration(MaterialCardDecoration(list, true))
        rv_index?.adapter = MainAdapter(list)
        callApiIndex(getService<MainService>().getData())
    }

    override fun onEmptyResponse(): ErrorViewModel {
        return ErrorType.ERROR_EMPTY_CUSTOM
    }
}