package ir.adp.framework

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import ir.adp.framework.data.manager.DataManager


open class ApplicationModule : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        val dataManager = DataManager(this)
        dataManager.put("direction", getDirection())
    }

    open fun getDirection(): String {
        return "ltr"
    }
}
