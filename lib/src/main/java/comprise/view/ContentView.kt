package comprise.view

import android.graphics.Canvas
import comprise.widget.ViewStyle

open class ContentView : View {

    lateinit var content: View

    constructor(
        style: ViewStyle=ViewStyle(),
        content: View? = null
    ) : super(style) {
        content?.let {
            this.content = content
        }
    }

    override fun measure() {
        content.measure()
        measuredWidth = content.measuredWidth
        measuredHeight = content.measuredHeight
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        content.layout(x, y, width, height)
    }

    override fun draw(canvas: Canvas) {
        content.draw(canvas)
    }
}
