package comprise.view

import android.graphics.Canvas
import android.view.MotionEvent

open class ViewContainer(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    minWidth: Int = 0,
    minHeight: Int = 0,
    name: String? = null,
    child: View? = null
) : View(width, height, minWidth, minHeight, name) {

    private var _child: View? = null
    var child: View?
        get() = _child
        set(value) {
            _child?.parent = null
            _child = value
            _child?.parent = this
        }

    init {
        this.child = child
    }

    override fun measure() {
        child?.let {
            it.measure()

            measuredWidth = when (desiredWidth) {
                LayoutSize.WRAP_CONTENT -> it.measuredWidth
                LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
                else -> desiredWidth.size
            }
            measuredHeight = when (desiredHeight) {
                LayoutSize.WRAP_CONTENT -> it.measuredHeight
                LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
                else -> desiredHeight.size
            }
        }
    }

    override fun touchEvent(ev: MotionEvent): Boolean {
        return child?.touchEvent(ev) ?: false
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        layoutChild(x, y, width, height)
    }

    open fun layoutChild(x: Int, y: Int, width: Int, height: Int) {
        child?.layout(0, 0, width, height)
    }

    override fun draw(canvas: Canvas, editMode: Boolean, debugMode: Boolean) {
        child?.draw(canvas, editMode, debugMode)
    }
}
