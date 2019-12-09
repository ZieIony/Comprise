package comprise.widget

import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewGroup
import kotlin.math.min

open class Stack : ViewGroup {

    constructor(views: List<View> = emptyList()) : super(views)

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
            LayoutSize.WRAP_CONTENT -> views.map { it.measuredHeight }.max() ?: 0
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)

        views.forEach {
            it.layout(it.x, it.y, min(it.measuredWidth, width), min(it.measuredHeight, height))
        }
    }
}
