package ir.adp.framework.base

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ir.adp.framework.R
import ir.adp.framework.data.api.ApiClient
import ir.adp.framework.data.api.ApiUtil
import ir.adp.framework.data.manager.DataManager
import ir.adp.framework.utils.DisplayHelper
import ir.adp.framework.utils.changeColorDrawable
import ir.adp.framework.utils.changeColorDrawableVector
import ir.adp.framework.utils.showAnimation
import ir.adp.widgets.Toolbar


open class BaseActivity : AppCompatActivity() {

    companion object {
        var DIRECTION = ""
        const val REQUEST_GPS_SETTINGS = 1
        const val REQUEST_GPS_SETTINGS_CANCELED = 2
    }

    protected var toolbar: Toolbar? = null
    var isShowBack = true
    var resBackIcon = R.drawable.ic_arrow_back
    var resMenu = 0
    lateinit var dataManager: DataManager

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        showAnimation()
        attachToolbar()

        dataManager = DataManager(application)
        ApiUtil.init(dataManager)
        DisplayHelper.changeAppDirection(this, DIRECTION)

        val icon =
            changeColorDrawableVector(
                this,
                DIRECTION,
                resBackIcon,
                ContextCompat.getColor(this, R.color.toolbar_color)
            )

        if (isShowBack) {
            toolbar?.navigationIcon = icon
            toolbar?.setNavigationOnClickListener {
                finish()
                showAnimation()
            }
        } else {
            if (resBackIcon != R.drawable.ic_arrow_back) {
                toolbar?.navigationIcon = icon
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return if (resMenu != 0) {
            menuInflater.inflate(resMenu, menu)
            for (i in 0 until menu!!.size()) {
                val m = menu.getItem(0)
                m.icon = changeColorDrawable(
                    this,
                    m.icon,
                    ContextCompat.getColor(this, R.color.toolbar_color)
                )
            }
            true
        } else super.onCreateOptionsMenu(menu)
    }

    private fun attachToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    fun setToolbarTitle(title: String) {
        toolbar?.textView?.text = title
    }

    fun setToolbarTitle(res: Int) {
        toolbar?.textView?.text = getString(res)
    }

    inline fun <reified T> getService(): T {
        val retrofit = ApiClient()
        val cs: Class<T> = Class.forName(T::class.java.name) as Class<T>
        return retrofit.getClient().create(cs)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showAnimation()
    }
}