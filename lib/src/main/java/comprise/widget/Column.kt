package comprise.widget

import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewGroup
import kotlin.math.min

open class Column : ViewGroup {

    constructor(
        views: List<View> = emptyList()
    ) : super(views)

    override fun measure() {
        views.forEach {
            it.measure()
        }

        measuredWidth = when (desiredWidth) {
            LayoutSize.WRAP_CONTENT -> views.map { it.measuredWidth }.max() ?: 0
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredWidth.size
        }
        measuredHeight = when (desiredHeight) {
            LayoutSize.WRAP_CONTENT -> views.map { it.measuredHeight }.sum()
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)

        var currentY = 0
        views.forEach {
            it.layout(it.x, currentY, min(it.measuredWidth, this.width), min(it.measuredHeight,
                this.height
            ))
            currentY += it.height
        }
    }
}

