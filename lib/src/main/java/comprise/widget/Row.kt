package comprise.widget

import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewGroup
import kotlin.math.min

open class Row(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.MATCH_PARENT,
    minWidth: Int = 0,
    minHeight: Int = 0,
    name: String? = null,
    children: List<View> = emptyList()
) : ViewGroup(width, height, minWidth, minHeight, name, children) {

    override fun measure() {
        children.forEach {
            it.measure()
        }

        measuredWidth = when (desiredWidth) {
            LayoutSize.WRAP_CONTENT -> children.filter { it.desiredWidth != LayoutSize.MATCH_PARENT }.map { it.measuredWidth }.sum()
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredWidth.size
        }
        measuredHeight = when (desiredHeight) {
            LayoutSize.WRAP_CONTENT -> children.filter { it.desiredHeight != LayoutSize.MATCH_PARENT }.map { it.measuredHeight }.max()
                ?: 0
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)

        var currentX = 0
        children.forEach {
            it.layout(
                currentX, y,
                min(it.measuredWidth, this.width),
                min(it.measuredHeight, this.height)
            )
            currentX += it.width
        }
    }
}
