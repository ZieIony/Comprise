package comprise.widget

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import comprise.view.LayoutSize
import comprise.view.View

class Image : View {
    var drawable: Drawable?

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        drawable: Drawable? = null
    ) : super(width, height) {
        this.drawable = drawable
    }

    override fun measure() {
        measuredWidth = when (desiredWidth) {
            LayoutSize.WRAP_CONTENT -> drawable?.intrinsicWidth ?: 0
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredWidth.size
        }
        measuredHeight = when (desiredHeight) {
            LayoutSize.WRAP_CONTENT -> drawable?.intrinsicHeight ?: 0
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    override fun draw(canvas: Canvas) {
        drawable?.apply {
            setBounds(0, 0, width, height)
            draw(canvas)
        }
    }

}
