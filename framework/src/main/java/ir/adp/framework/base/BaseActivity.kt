package ir.adp.framework.base

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import ir.adp.framework.R
import ir.adp.framework.data.api.ApiClient
import ir.adp.framework.data.manager.DataManager
import ir.adp.framework.utils.DisplayHelper
import ir.adp.framework.utils.changeColorDrawableRes
import ir.adp.framework.utils.showAnimation
import ir.adp.widgets.Toolbar


open class BaseActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    var isShowBack: Boolean = false
    var resBackIcon: Int = R.drawable.ic_arrow_back
    lateinit var dataManager: DataManager

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        showAnimation()
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        dataManager = DataManager(application)
        DisplayHelper.changeAppDirection(this, getDirection())
        val icon = changeColorDrawableRes(this, resBackIcon, ContextCompat.getColor(this, R.color.toolbar_color))

        if (isShowBack) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.navigationIcon = icon
            toolbar.setNavigationOnClickListener {
                finish()
                showAnimation()
            }
        } else {
            if (resBackIcon != R.drawable.ic_arrow_back) {
                toolbar.navigationIcon = icon
            }
        }
    }

    fun setToolbarTitle(title: String) {
        toolbar.textView.text = title
    }

    fun setToolbarTitle(res: Int) {
        toolbar.textView.text = getString(res)
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

    open fun getDirection(): String {
        return "ltr"
    }
}