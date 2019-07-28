package ir.adp.kriptonframework

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import ir.adp.framework.base.BaseActivity
import ir.adp.framework.utils.goActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    init {
        resBackIcon = R.drawable.ic_menu
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbarTitle(R.string.app_name)

        bnv.addItem("home", R.drawable.ic_error)
        bnv.addItem("notification", R.drawable.ic_warning)
        bnv.addItem("error", R.drawable.ic_no_internet)
        bnv.addItem("sfv", R.drawable.ic_no_internet)
        bnv.addItem("fb", R.drawable.ic_no_internet)

        bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                1 -> supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
                2 -> supportFragmentManager.beginTransaction().replace(R.id.container, MainIndexFragment()).commit()
                3 -> supportFragmentManager.beginTransaction().replace(R.id.container, Fragment()).commit()
                4 -> supportFragmentManager.beginTransaction().replace(R.id.container, MainIndexFragment()).commit()
                5 -> supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
            }
            return@setOnNavigationItemSelectedListener true
        }

        dataManager.put("useraccount", UserModel("ali", 17, "isfahan"))
        val user = dataManager.get("useraccount", UserModel())
        Log.d("awrbgRWV", user.name)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.submit -> goActivity(MainIndexActivity::class.java)
            R.id.form -> goActivity(Activity::class.java)
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}