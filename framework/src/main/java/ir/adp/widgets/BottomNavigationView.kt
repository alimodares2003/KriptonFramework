package ir.adp.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.adp.framework.R
import ir.adp.framework.utils.CustomTypefaceSpan

class BottomNavigationView : BottomNavigationView {

    var itemId = 1

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    @SuppressLint("ResourceType")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        changeBottomNavigationFont()
        selectedItemId = 1
    }

    fun addItem(title: String, resIcon: Int) {
        menu.add(Menu.NONE, itemId++, Menu.NONE, title).setIcon(resIcon)
    }

    private fun changeBottomNavigationFont() {
        val font = Typeface.createFromAsset(context.assets, context.getString(R.string.font_mainRegular))
        val typefaceSpan = CustomTypefaceSpan("", font)
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            val spannableTitle = SpannableStringBuilder(menuItem.title)
            spannableTitle.setSpan(typefaceSpan, 0, spannableTitle.length, 0)
            menuItem.title = spannableTitle
        }
    }

}