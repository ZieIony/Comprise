package comprise.view

import android.graphics.Canvas
import android.graphics.Path

class Clip(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    child: View,
    var path: Path
) : ViewContainer(width, height, child = child) {

    override fun draw(canvas: Canvas, editMode: Boolean, debugMode: Boolean) {
        val saveCount = canvas.save()
        canvas.clipPath(path)
        super.draw(canvas, editMode, debugMode)
        canvas.restoreToCount(saveCount)
    }
}
