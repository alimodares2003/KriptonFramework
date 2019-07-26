package ir.adp.framework.utils.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ir.adp.framework.R
import ir.adp.framework.utils.DisplayHelper

@Suppress("unused")
class DividerDecoration(context: Context?, marginStart: Int = 0, marginEnd: Int = 0) : RecyclerView.ItemDecoration() {

    private val divider = GradientDrawable()
    private var marginRight: Int
    private var marginLeft: Int

    init {
        divider.shape = GradientDrawable.RECTANGLE
        divider.setSize(1, 1)
        divider.setColor(ContextCompat.getColor(context!!, R.color.divider_light))

        marginLeft = DisplayHelper.i(marginStart)
        marginRight = DisplayHelper.i(marginEnd)

//        if (MeowControllerHelper.isRtl()) {
//            val holder = marginLeft
//            marginLeft = marginRight
//            marginRight = holder
//        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = DisplayHelper.i(1)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        drawVertical(c, parent)
    }

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft + marginLeft
        val right = parent.width - parent.paddingRight - marginRight

        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
    //
    //    public void drawHorizontal(Canvas c, RecyclerView parent) {
    //        final int top = parent.getPaddingTop();
    //        final int bottom = parent.getHeight() - parent.getPaddingBottom();
    //
    //        final int childCount = parent.getChildCount();
    //        for (int i = 0; i < childCount; i++) {
    //            final View child = parent.getChildAt(i);
    //            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
    //                    .getLayoutParams();
    //            final int left = child.getRight() + params.rightMargin;
    //            final int right = left + divider.getIntrinsicHeight();
    //            divider.setBounds(left, top, right, bottom);
    //            divider.draw(c);
    //        }
    //    }
}