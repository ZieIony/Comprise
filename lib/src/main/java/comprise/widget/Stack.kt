package comprise.widget

import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewGroup
import kotlin.math.min

open class Stack(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    minWidth: Int = 0,
    minHeight: Int = 0,
    name: String? = null,
    var verticalGravity: VerticalGravity = VerticalGravity.TOP,
    var horizontalGravity: HorizontalGravity = HorizontalGravity.LEFT,
    children: List<View> = emptyList()
) : ViewGroup(width, height, minWidth, minHeight, name, children) {

    override fun measure() {
        children.forEach {
            it.measure()
        }

        measuredWidth = when (desiredWidth) {
            LayoutSize.WRAP_CONTENT -> {
                children.filter { it.desiredWidth != LayoutSize.MATCH_PARENT }.map { it.measuredWidth }.max()
                    ?: 0
            }
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredWidth.size
        }
        measuredHeight = when (desiredHeight) {
            LayoutSize.WRAP_CONTENT -> {
                children.filter { it.desiredHeight != LayoutSize.MATCH_PARENT }.map { it.measuredHeight }.max()
                    ?: 0
            }
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)

        children.forEach {
            val w = min(it.measuredWidth, width)
            val h = min(it.measuredHeight, height)
            val itx = when (horizontalGravity) {
                HorizontalGravity.LEFT -> 0
                HorizontalGravity.RIGHT -> width - w
                else -> (width - w) / 2
            }
            val ity = when (verticalGravity) {
                VerticalGravity.TOP -> 0
                VerticalGravity.BOTTOM -> height - h
                else -> (height - h) / 2
            }
            it.layout(itx, ity, w, h)
        }
    }
}
