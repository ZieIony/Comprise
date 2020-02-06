package comprise.widget

import android.graphics.drawable.Drawable
import android.view.GestureDetector
import android.view.MotionEvent
import comprise.view.ContentView
import comprise.view.LayoutSize
import comprise.view.View

class Button(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    background: Drawable? = null,
    content: View? = null,
    var onClick: ((MotionEvent) -> Unit)? = null
) : ContentView(width, height) {

    private val gestureDetector =
        GestureDetector(object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                onClick?.invoke(e)
                return true
            }
        })

    private val backgroundImage = Image(
        LayoutSize.MATCH_PARENT,
        LayoutSize.MATCH_PARENT,
        drawable = background
    )

    init {
        this.content = Stack(
            width, height,
            views = listOfNotNull(
                backgroundImage,
                content
            )
        )
    }

    override fun touchEvent(ev: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(ev)
    }
}
