package comprise.view

import android.graphics.Canvas
import android.graphics.Path

class Clip(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    content: View,
    var path: Path
) : ContentView(width, height, content) {

    override fun draw(canvas: Canvas) {
        val saveCount = canvas.save()
        canvas.clipPath(path)
        super.draw(canvas)
        canvas.restoreToCount(saveCount)
    }
}
