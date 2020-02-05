package comprise.widget

import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewGroup
import kotlin.math.min

open class Column : ViewGroup {

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        views: List<View> = emptyList()
    ) : super(width, height, views)

    override fun measure() {
        views.forEach {
            it.measure()
        }

        measuredWidth = when (desiredWidth) {
            LayoutSize.WRAP_CONTENT -> views.filter { it.desiredWidth != LayoutSize.MATCH_PARENT }.map { it.measuredWidth }.max()
                ?: 0
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredWidth.size
        }
        measuredHeight = when (desiredHeight) {
            LayoutSize.WRAP_CONTENT -> views.filter { it.desiredHeight != LayoutSize.MATCH_PARENT }.map { it.measuredHeight }.sum()
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)

        var currentY = 0
        views.forEach {
            it.layout(
                it.x,
                currentY,
                min(it.measuredWidth, this.width),
                min(it.measuredHeight, this.height)
            )
            currentY += it.height
        }
    }
}

