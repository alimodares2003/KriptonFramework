package ir.adp.framework.utils.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ir.adp.framework.utils.DisplayHelper

@Suppress("unused")
class MaterialCardDecoration(
    private var list: List<*>? = null,
    private var hasShadow: Boolean = true,
    private var spanCount: Int = 1,
    private var gapSize: Int = 8,
    private var gapStartSize: Int = 8,
    private var orientation: Int = ORIENTATION_VERTICAL,
    private var shadowSize: Int = 4,
    private var layoutManager: RecyclerView.LayoutManager? = null
) : RecyclerView.ItemDecoration() {

    companion object {
        const val ORIENTATION_VERTICAL = 1
        const val ORIENTATION_HORIZONTAL = 2
    }

    init {
        gapSize = if (hasShadow) gapSize - shadowSize else gapSize
        gapSize = DisplayHelper.i(gapSize)

        gapStartSize = if (hasShadow) gapStartSize - shadowSize else gapStartSize
        gapStartSize = DisplayHelper.i(gapStartSize)

        if (gapSize < 0)
            gapSize = 0
        if (gapStartSize < 0)
            gapStartSize = 0
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        val m = if (position % spanCount == 0) position else position - 1

        if (orientation == ORIENTATION_VERTICAL) {
            if (m == 0)
                outRect.top = gapStartSize

            if (spanCount == 1) {
                outRect.left = gapSize
                outRect.right = gapSize
            } else {
                if (position % spanCount == 0) {//left
                    outRect.left = gapSize
                    outRect.right = calculateGapSize() / 2
                } else {//right
                    outRect.left = calculateGapSize() / 2
                    outRect.right = gapSize
                }
            }

            outRect.bottom = calculateGapSize()

//            calculateForRtl(outRect)
        }

        if (orientation == ORIENTATION_HORIZONTAL) {
            if (m == 0)
                outRect.left = gapStartSize

            outRect.right = calculateGapSize()
//            calculateForRtl(outRect)
        }

    }

//    private fun calculateForRtl(outRect: Rect) {
//        if (MeowControllerHelper.isRtl()) {
//            val holder = outRect.left
//            outRect.left = outRect.right
//            outRect.right = holder
//        }
//    }

    private fun calculateGapSize() = if (hasShadow) gapSize - DisplayHelper.i(shadowSize) else gapSize

}