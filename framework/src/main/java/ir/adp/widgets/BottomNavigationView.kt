package ir.adp.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationView : BottomNavigationView {

    var itemId = 1

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    @SuppressLint("ResourceType")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        selectedItemId = 1
    }

    fun addItem(title: String, resIcon: Int) {
        menu.add(Menu.NONE, itemId++, Menu.NONE, title).setIcon(resIcon)
    }


}