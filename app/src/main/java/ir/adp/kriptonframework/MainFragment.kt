package ir.adp.kriptonframework

import android.os.Bundle
import android.view.View
import ir.adp.framework.base.BaseFragment
import ir.adp.framework.utils.goActivity
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {

    init {
        layout = R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt.setOnClickListener {
            goActivity(MainIndexActivity::class.java)
        }
    }

}