package comprise.view

import android.graphics.Canvas
import android.view.MotionEvent

class Padding : ViewContainer {

    var paddingLeft: Int = 0
    var paddingTop: Int = 0
    var paddingRight: Int = 0
    var paddingBottom: Int = 0

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        child: View? = null,
        paddingLeft: Int = 0,
        paddingTop: Int = 0,
        paddingRight: Int = 0,
        paddingBottom: Int = 0
    ) : super(width, height, child = child) {
        this.paddingLeft = paddingLeft
        this.paddingTop = paddingTop
        this.paddingRight = paddingRight
        this.paddingBottom = paddingBottom
    }

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        child: View? = null,
        padding: Int = 0
    ) : this(width, height, child, padding, padding, padding, padding)

    override fun measure() {
        child?.let {
            it.measure()

            measuredWidth = when (desiredWidth) {
                LayoutSize.WRAP_CONTENT -> it.measuredWidth + paddingLeft + paddingRight
                LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
                else -> desiredWidth.size
            }
            measuredHeight = when (desiredHeight) {
                LayoutSize.WRAP_CONTENT -> it.measuredHeight + paddingTop + paddingBottom
                LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
                else -> desiredHeight.size
            }
        }
    }

    override fun layoutChild(x: Int, y: Int, width: Int, height: Int) {
        child?.layout(
            paddingLeft,
            paddingTop,
            width - paddingLeft - paddingRight,
            height - paddingTop - paddingBottom
        )
    }

    override fun draw(canvas: Canvas, editMode: Boolean, debugMode: Boolean) {
        val saveCount = canvas.save()
        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
        child?.draw(canvas, editMode, debugMode)
        canvas.restoreToCount(saveCount)
    }

    override fun touchEvent(ev: MotionEvent): Boolean {
        val event = MotionEvent.obtain(ev)
        event.offsetLocation(-paddingLeft.toFloat(), -paddingTop.toFloat())
        val result = super.touchEvent(event)
        event.recycle()
        return result
    }
}
