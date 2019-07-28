package ir.adp.kriptonframework


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.adp.framework.base.index.BaseIndexActivity
import ir.adp.framework.utils.decoration.MaterialCardDecoration


class MainIndexActivity : BaseIndexActivity<MainResponse>() {

    init {
        hasToolbarElevationOnScrollListener = true
        isShowBack = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setToolbarTitle(R.string.app_name)

        rv_index?.layoutManager = LinearLayoutManager(this)
        rv_index?.addItemDecoration(MaterialCardDecoration(list, true))
        rv_index?.adapter = MainAdapter(list)

        callApiIndex(getService<MainService>().getData())

    }

}
