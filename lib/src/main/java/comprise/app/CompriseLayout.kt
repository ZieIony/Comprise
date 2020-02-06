package comprise.app

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import comprise.view.View

open class CompriseLayout : FrameLayout {

    val views = mutableListOf<View>()

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setLayerType(android.view.View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        views.forEach {
            it.measure()
            it.layout(0, 0, width, height)
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)

        views.forEach {
            it.draw(canvas)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        views.forEach {
            if (it.x <= ev.x && it.y <= ev.y && (it.x + it.width) >= ev.x && (it.y + it.height) >= ev.y)
                if (it.touchEvent(ev))
                    return true
        }

        return true
    }
}
