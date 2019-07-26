package ir.adp.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.adp.framework.data.api.ApiClient
import ir.adp.framework.data.manager.DataManager

open class BaseFragment : Fragment() {

    var layout: Int = 0
    lateinit var dataManager: DataManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataManager = DataManager(activity!!.application)
        return inflater.inflate(layout, container, false)
    }

    inline fun <reified T> getService(): T {
        val retrofit = ApiClient()
        val cs: Class<T> = Class.forName(T::class.java.name) as Class<T>
        return retrofit.getClient().create(cs)
    }

}