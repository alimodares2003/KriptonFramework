package ir.adp.framework.data.api

import android.content.Context
import ir.adp.framework.data.manager.DataManager
import ir.adp.framework.utils.clearCache
import ir.adp.framework.utils.restartApplication

/**
 * Created by Ali on 10/8/2019.
 */
object ApiUtil {

    private var dataManager: DataManager? = null
    fun init(dataManager: DataManager) {
        this.dataManager = dataManager
    }

    fun localLogOut(context: Context) {
        clearCache(context)
        dataManager?.clear()
        context.restartApplication()
    }
}