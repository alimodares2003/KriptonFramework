package ir.adp.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.Menu
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import ir.adp.framework.R
import ir.adp.framework.utils.CustomTypefaceSpan

class BottomNavigationView : LinearLayout {

    private var itemId = 1
    private lateinit var container: FrameLayout
    lateinit var bottomNavigation: BottomNavigationView
    private lateinit var supportFragmentManager: FragmentManager
    private val list: ArrayList<Fragment> = ArrayList()
    private var activeFragment: Fragment? = null
    private var isRefresh: Boolean = false
    private var itemMode = 0


    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    @Suppress("unused")
    fun init(
        supportFragmentManager: FragmentManager,
        isRefresh: Boolean = false,
        labelVisibilityMode: Int = LabelVisibilityMode.LABEL_VISIBILITY_AUTO
    ): MyBottomNavigation {
        this.itemMode = labelVisibilityMode
        this.isRefresh = isRefresh
        this.supportFragmentManager = supportFragmentManager
        return MyBottomNavigation()
    }

    open inner class MyBottomNavigation {

        @Suppress("unused")
        fun addItem(title: String, resIcon: Int, fragment: Fragment): MyBottomNavigation {
            list.add(fragment)
            bottomNavigation.menu.add(Menu.NONE, itemId++, Menu.NONE, title).setIcon(resIcon)
            if (!isRefresh)
                supportFragmentManager.beginTransaction().add(
                    container.id,
                    fragment
                ).hide(fragment).commit()
            return MyBottomNavigation()
        }

        @Suppress("unused")
        fun build() {
            bottomNavigation.labelVisibilityMode = itemMode
            activeFragment = list[0]
            bottomNavigation.setOnNavigationItemSelectedListener {
                for (i in 1..bottomNavigation.menu.size()) {
                    val position = i - 1
                    when (it.itemId) {
                        i -> {
                            if (!isRefresh) {
                                supportFragmentManager.beginTransaction().hide(activeFragment!!)
                                    .show(list[position]).commit()
                                activeFragment = list[position]
                            } else
                                supportFragmentManager.beginTransaction().replace(
                                    container.id,
                                    list[position]
                                ).commit()
                        }
                    }

                }
                return@setOnNavigationItemSelectedListener true
            }
        }

        @Suppress("unused")
        @SuppressLint("PrivateResource", "ResourceType")
        fun styleColored(): MyBottomNavigation {
            bottomNavigation.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimary
                )
            )
            bottomNavigation.isItemHorizontalTranslationEnabled = false
            bottomNavigation.itemTextColor =
                ContextCompat.getColorStateList(context, R.color.mtrl_bottom_nav_colored_item_tint)
            bottomNavigation.itemTextAppearanceActive = R.attr.textAppearanceCaption
            bottomNavigation.itemTextAppearanceInactive = R.attr.textAppearanceCaption
            bottomNavigation.itemIconTintList =
                ContextCompat.getColorStateList(context, R.color.mtrl_bottom_nav_colored_item_tint)
            return MyBottomNavigation()
        }

        @Suppress("unused")
        @SuppressLint("PrivateResource", "ResourceType")
        fun styleDefault(): MyBottomNavigation {
            bottomNavigation.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            bottomNavigation.isItemHorizontalTranslationEnabled = false
            bottomNavigation.itemTextColor =
                ContextCompat.getColorStateList(context, R.color.mtrl_bottom_nav_item_tint)
            bottomNavigation.itemTextAppearanceActive = R.attr.textAppearanceCaption
            bottomNavigation.itemTextAppearanceInactive = R.attr.textAppearanceCaption
            bottomNavigation.itemIconTintList =
                ContextCompat.getColorStateList(context, R.color.mtrl_bottom_nav_item_tint)
            return MyBottomNavigation()
        }

    }

    private fun initView(context: Context) {
        orientation = VERTICAL
        val containerParams = LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f)
        container = FrameLayout(context)
        container.layoutParams = containerParams
        container.id = View.generateViewId()
        addView(container)

        val bnvParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        bottomNavigation = BottomNavigationView(context)
        bottomNavigation.layoutParams = bnvParams
        addView(bottomNavigation)
    }

    @SuppressLint("ResourceType")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        changeBottomNavigationFont()
        bottomNavigation.selectedItemId = 1
    }

    private fun changeBottomNavigationFont() {
        val font = Typeface.createFromAsset(context.assets, context.getString(R.string.font_mainRegular))
        val typefaceSpan = CustomTypefaceSpan("", font)
        for (i in 0 until bottomNavigation.menu.size()) {
            val menuItem = bottomNavigation.menu.getItem(i)
            val spannableTitle = SpannableStringBuilder(menuItem.title)
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length, 0)
            menuItem.title = spannableTitle
        }
    }

}