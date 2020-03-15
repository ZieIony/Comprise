package comprise.view

import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.view.MotionEvent

class Ripple(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    content: View? = null
) : ContentView(width, height, content) {

    private val rippleDrawable = RippleDrawable(ColorStateList.valueOf(Color.WHITE), null, null)

    private val callback = object : Drawable.Callback {
        override fun unscheduleDrawable(who: Drawable, what: Runnable) {
        }

        override fun invalidateDrawable(who: Drawable) {
            requestDraw()
        }

        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
        }
    }

    init {
        rippleDrawable.callback = callback
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        rippleDrawable.setBounds(0, 0, width, height)
    }

    override fun draw(canvas: Canvas) {
        rippleDrawable.draw(canvas)
        super.draw(canvas)
    }

    override fun touchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            rippleDrawable.setHotspot(ev.x, ev.y)
            rippleDrawable.state = intArrayOf(
                android.R.attr.state_enabled,
                android.R.attr.state_pressed
            )
        } else if (ev.action == MotionEvent.ACTION_MOVE) {
            rippleDrawable.setHotspot(ev.x, ev.y)
        } else if (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_CANCEL) {
            rippleDrawable.state = intArrayOf(android.R.attr.state_enabled)
        }
        super.touchEvent(ev)
        return true
    }
}
