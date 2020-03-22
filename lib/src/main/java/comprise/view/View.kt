package comprise.view

import android.graphics.Canvas
import android.view.MotionEvent
import kotlin.math.max

abstract class View(
    var desiredWidth: LayoutSize = LayoutSize.WRAP_CONTENT,
    var desiredHeight: LayoutSize = LayoutSize.WRAP_CONTENT,
    var minWidth: Int = 0,
    var minHeight: Int = 0,
    var name: String? = null
) {
    var x = 0
    var y = 0

    var measuredWidth = 0   // what the view wants
    var measuredHeight = 0
    var width = 0 // what was finally mediated
    var height = 0

    var parent: View? = null

    open fun measure() {
        measuredWidth = when (desiredWidth) {
            LayoutSize.WRAP_CONTENT -> minWidth
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredWidth.size
        }
        measuredHeight = when (desiredHeight) {
            LayoutSize.WRAP_CONTENT -> minHeight
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    open fun layout(x: Int, y: Int, width: Int, height: Int) {
        this.x = x
        this.y = y
        this.width = max(0, width)
        this.height = max(0, height)
    }

    open fun draw(canvas: Canvas, editMode: Boolean = false, debugMode: Boolean = false) {}

    open fun touchEvent(ev: MotionEvent) = false

    open fun requestLayout() {
        parent?.requestLayout()
    }

    open fun requestDraw() {
        parent?.requestDraw()
    }

    val state: ViewState = ViewState(this)

    open fun onStateChanged() {}
}
