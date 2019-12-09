package comprise.view

import android.graphics.Canvas
import android.graphics.Path

class Clip : ContentView {

    var path: Path

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        content: View,
        path: Path
    ) : super(width, height, content) {
        this.path = path
    }

    override fun draw(canvas: Canvas) {
        val saveCount = canvas.save()
        canvas.clipPath(path)
        super.draw(canvas)
        canvas.restoreToCount(saveCount)
    }
}
