package ir.adp.framework

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import ir.adp.framework.base.BaseActivity
import ir.adp.framework.data.api.ApiClient


open class ApplicationModule : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        ApiClient.BASE_URL = getBaseUrl()
        BaseActivity.DIRECTION = getDirection()
//        DataManager.KEYNAME = getAppPackageName()
    }

    open fun getDirection(): String {
        return "ltr"
    }

    open fun getBaseUrl(): String {
        return ""
    }

    open fun getAppPackageName(): String {
        return ""
    }
}
