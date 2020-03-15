package comprise.view

import android.graphics.Canvas
import android.view.MotionEvent

open class ContentView(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    content: View? = null
) : View(width, height) {

    private var _content: View? = null
    open var content: View
        get() = _content!!
        set(value) {
            _content?.parent = null
            _content = value
            value.parent = this
        }

    init {
        content?.let {
            this.content = it
        }
    }

    override fun measure() {
        content.measure()

        measuredWidth = when (desiredWidth) {
            LayoutSize.WRAP_CONTENT -> content.measuredWidth
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredWidth.size
        }
        measuredHeight = when (desiredHeight) {
            LayoutSize.WRAP_CONTENT -> content.measuredHeight
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    override fun touchEvent(ev: MotionEvent): Boolean {
        return content.touchEvent(ev)
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        content.layout(0, 0, width, height)
    }

    override fun draw(canvas: Canvas) {
        content.draw(canvas)
    }
}
