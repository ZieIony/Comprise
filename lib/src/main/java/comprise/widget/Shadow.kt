package comprise.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import comprise.view.ContentView
import comprise.view.LayoutSize
import comprise.view.View

class Shadow(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    content: View,
    var path: Path,
    var radius: Float = 2.0f * 3,
    var color: Int = 0x7f000000
) : ContentView(width, height, content) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun draw(canvas: Canvas) {
        paint.color = Color.WHITE
        paint.setShadowLayer(radius, 0.0f, radius / 2, color)
        canvas.drawPath(path, paint)
        super.draw(canvas)
    }
}