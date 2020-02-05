package comprise.view

import android.graphics.Canvas

abstract class ViewGroup : View {

    val views = mutableListOf<View>()

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        views: List<View> = emptyList()
    ) : super(width, height) {
        this.views.addAll(views)
    }

    override fun draw(canvas: Canvas) {
        views.forEach {
            val saveCount = canvas.save()
            canvas.translate(it.x.toFloat(), it.y.toFloat())
            it.draw(canvas)
            canvas.restoreToCount(saveCount)
        }
    }
}
