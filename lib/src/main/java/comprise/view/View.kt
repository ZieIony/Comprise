package comprise.view

import android.graphics.Canvas
import android.view.MotionEvent
import comprise.widget.ViewStyle

abstract class View : ViewStyle {
    var style: ViewStyle

    var x = 0
    var y = 0
    override var desiredWidth: LayoutSize   // what the user wants
    override var desiredHeight: LayoutSize

    var measuredWidth = 0   // what the view wants
    var measuredHeight = 0
    var width = 0 // what was finally mediated
    var height = 0

    constructor(
        style: ViewStyle
    ) {
        this.style = style

        this.desiredWidth = style.desiredWidth
        this.desiredHeight = style.desiredHeight
    }

    open fun measure() {
        measuredWidth = when (desiredWidth) {
            LayoutSize.WRAP_CONTENT -> 0
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredWidth.size
        }
        measuredHeight = when (desiredHeight) {
            LayoutSize.WRAP_CONTENT -> 0
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    open fun layout(x: Int, y: Int, width: Int, height: Int) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    open fun draw(canvas: Canvas) {}

    open fun touchEvent(ev: MotionEvent) = false
}

