package comprise.app

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import comprise.view.LayoutSize

open class CompriseLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {

    private val root = RootLayout(LayoutSize.MATCH_PARENT, LayoutSize.MATCH_PARENT, androidView = this)

    val views = root.views

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        root.measure()
        root.layout(0, 0, width, height)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)

        root.draw(canvas)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return root.touchEvent(ev)
    }
}
