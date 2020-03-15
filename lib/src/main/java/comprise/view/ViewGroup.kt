package comprise.view

import android.graphics.Canvas
import android.view.MotionEvent

abstract class ViewGroup(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    views: List<View> = emptyList()
) : View(width, height) {

    val views = ViewList(this)

    init {
        this.views.addAll(views)
    }

    override fun draw(canvas: Canvas) {
        views.forEach {
            val saveCount = canvas.save()
            canvas.translate(it.x.toFloat(), it.y.toFloat())
            it.draw(canvas)
            canvas.restoreToCount(saveCount)
        }
    }

    override fun touchEvent(ev: MotionEvent): Boolean {
        views.forEach {
            val event = MotionEvent.obtain(ev)
            event.setLocation(event.x - it.x.toFloat(), event.y - it.y.toFloat())
            if (event.x >= 0 && event.y >= 0 && event.x <= it.width && event.y <= it.height)
                if (it.touchEvent(event))
                    return true
        }
        return super.touchEvent(ev)
    }
}
