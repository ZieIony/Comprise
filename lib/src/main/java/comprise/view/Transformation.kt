package comprise.view

import android.graphics.Canvas
import android.graphics.PointF

class Transformation(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    child: View,
    var translation: PointF = PointF(),
    var scale: PointF = PointF(1.0f, 1.0f),
    var rotation: Float = 0.0f,
    var pivot: PointF = PointF()
) : ViewContainer(width, height, child = child) {

    override fun draw(canvas: Canvas, editMode: Boolean, debugMode: Boolean) {
        val saveCount = canvas.save()
        canvas.scale(scale.x, scale.y, pivot.x, pivot.y)
        canvas.rotate(rotation, pivot.x, pivot.y)
        canvas.translate(translation.x, translation.y)
        super.draw(canvas, editMode, debugMode)
        canvas.restoreToCount(saveCount)
    }
}
