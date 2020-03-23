package comprise.widget

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import comprise.dp
import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewContainer


class Border(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    child: View,
    var path: Path,
    color: Int = 0x7f000000,
    strokeWidth: Float = 2.0f.dp
) : ViewContainer(width, height, child = child) {

    private var _color = color
    var color: Int
        get() = _color
        set(value) {
            _color = value
            paint.color = _color
        }

    private var _strokeWidth = strokeWidth
    var strokeWidth: Float
        get() = _strokeWidth
        set(value) {
            _strokeWidth = value
            paint.strokeWidth = _strokeWidth
        }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }

    override fun draw(canvas: Canvas, editMode: Boolean, debugMode: Boolean) {
        super.draw(canvas, editMode, debugMode)
        canvas.drawPath(path, paint)
    }

}