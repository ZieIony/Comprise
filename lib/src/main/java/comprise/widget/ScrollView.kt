package comprise.widget

import android.graphics.Canvas
import android.graphics.PointF
import android.view.MotionEvent
import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewContainer
import kotlin.math.max
import kotlin.math.min


class ScrollView(
    width: LayoutSize = LayoutSize.MATCH_PARENT,
    height: LayoutSize = LayoutSize.MATCH_PARENT,
    minWidth: Int = 0,
    minHeight: Int = 0,
    name: String? = null,
    child: View? = null
) : ViewContainer(width, height, minWidth, minHeight, name, child) {

    val scroll = PointF()

    override fun draw(canvas: Canvas, editMode: Boolean, debugMode: Boolean) {
        val saveCount = canvas.save()
        canvas.clipRect(0, 0, width, height)
        canvas.translate(scroll.x, scroll.y)
        super.draw(canvas, editMode, debugMode)
        canvas.restoreToCount(saveCount)
    }

    override fun layoutChild(x: Int, y: Int, width: Int, height: Int) {
        child?.let {
            it.layout(
                0,
                0,
                if (it.desiredWidth == LayoutSize.MATCH_PARENT) width else it.measuredWidth,
                if (it.desiredHeight == LayoutSize.MATCH_PARENT) height else it.measuredHeight
            )
        }
    }

    private val prevTouch = PointF()
    override fun touchEvent(ev: MotionEvent): Boolean {
        child?.let {
            if (ev.action == MotionEvent.ACTION_MOVE) {
                val maxScrollX = (it.width - width).toFloat()
                scroll.x = max(0.0f, min(scroll.x + ev.x - prevTouch.x, maxScrollX))
                val maxScrollY = (it.height - height).toFloat()
                scroll.y = max(-maxScrollY, min(scroll.y + ev.y - prevTouch.y, 0.0f))
                requestDraw()
            }
            prevTouch.x = ev.x
            prevTouch.y = ev.y
        }
        val event = MotionEvent.obtain(ev)
        event.offsetLocation(-scroll.x, -scroll.y)
        val result = super.touchEvent(event)
        event.recycle()
        return result
    }
}