package comprise.view

import android.graphics.Canvas

abstract class ViewGroup : View {

    val views = mutableListOf<View>()

    constructor(views: List<View> = emptyList()) : super() {
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
