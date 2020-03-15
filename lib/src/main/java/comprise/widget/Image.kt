package comprise.widget

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import comprise.view.LayoutSize
import comprise.view.View

class Image(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    var drawable: Drawable? = null
) : View(width, height) {

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

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        drawable?.bounds?.set(0, 0, width, height)
    }

    override fun draw(canvas: Canvas) {
        drawable?.draw(canvas)
    }

}
