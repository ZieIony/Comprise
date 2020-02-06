package comprise.view

import android.graphics.Canvas
import android.view.MotionEvent

open class ContentView : View {

    lateinit var content: View

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        content: View? = null
    ) : super(width, height) {
        content?.let {
            this.content = content
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
