package comprise.view

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.max

class Ripple : ContentView {
    private val paint = Paint()

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        content: View
    ) : super(width, height, content)

    override fun draw(canvas: Canvas) {
        paint.color = 0x2fffffff
        canvas.drawCircle(
            ((x + width) / 2).toFloat(),
            (y + height) / 2.toFloat(),
            max(width, height) / 2.toFloat(),
            paint
        )

        super.draw(canvas)
    }
}
