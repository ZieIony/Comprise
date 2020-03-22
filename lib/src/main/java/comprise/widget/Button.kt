package comprise.widget

import android.graphics.drawable.Drawable
import android.view.GestureDetector
import android.view.MotionEvent
import comprise.theme.ButtonStyle
import comprise.view.ContentView
import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewContainer

class Button(
    style: ButtonStyle,
    width: LayoutSize = style.width,
    height: LayoutSize = style.height,
    minWidth: Int = style.minWidth,
    minHeight: Int = style.minHeight,
    name: String? = null,
    background: Drawable? = style.background,
    content: View? = null,
    var onClick: ((MotionEvent) -> Unit)? = null
) : ViewContainer(width, height, minWidth, minHeight, name), ContentView {

    private val gestureDetector =
        GestureDetector(object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                onClick?.invoke(e)
                return true
            }
        })

    private val backgroundImage = ImageView(
        LayoutSize.MATCH_PARENT,
        LayoutSize.MATCH_PARENT,
        drawable = background
    )

    private val container = ViewContainer(width, height, child = content)

    override var content: View?
        get() = container.child
        set(value) {
            container.child = value
        }

    init {
        this.child = Stack(
            width, height,
            children = listOfNotNull(
                backgroundImage,
                container
            )
        )
    }

    override fun touchEvent(ev: MotionEvent): Boolean {
        super.touchEvent(ev)
        return gestureDetector.onTouchEvent(ev)
    }
}
