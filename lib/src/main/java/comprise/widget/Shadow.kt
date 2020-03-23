package comprise.widget

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import comprise.dp
import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewContainer

class Shadow(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    child: View,
    var path: Path,
    var elevation: StateList<Float> = StateList(
        2.0f.dp, listOf(
            intArrayOf(android.R.attr.state_pressed) to 4.0f.dp
        )
    ),
    var color: Int = 0x7f000000
) : ViewContainer(width, height, child = child) {

    private var z: Float = elevation.getValue(intArrayOf(0))

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun draw(canvas: Canvas, editMode: Boolean, debugMode: Boolean) {
        paint.color = Color.WHITE
        paint.setShadowLayer(z, 0.0f, z / 2, color)
        canvas.drawPath(path, paint)
        super.draw(canvas, editMode, debugMode)
    }

    override fun touchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            state.add(android.R.attr.state_pressed)
        } else if (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_CANCEL) {
            state.clear(android.R.attr.state_pressed)
        }
        super.touchEvent(ev)
        return true
    }

    override fun onStateChanged() {
        val animator = ValueAnimator.ofFloat(
            z, elevation.getValue(state.get().toIntArray())
        )
        animator.duration = 200
        animator.addUpdateListener {
            z = it.animatedValue as Float
            requestDraw()
        }
        animator.start()
    }

}