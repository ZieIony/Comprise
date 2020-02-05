package comprise.view

import android.graphics.Canvas

class Padding : View {

    lateinit var content: View

    var paddingLeft: Int = 0
    var paddingTop: Int = 0
    var paddingRight: Int = 0
    var paddingBottom: Int = 0

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        content: View? = null,
        paddingLeft: Int = 0,
        paddingTop: Int = 0,
        paddingRight: Int = 0,
        paddingBottom: Int = 0
    ) : super(width, height) {
        content?.let {
            this.content = content
        }

        this.paddingLeft = paddingLeft
        this.paddingTop = paddingTop
        this.paddingRight = paddingRight
        this.paddingBottom = paddingBottom
    }

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        content: View? = null,
        padding: Int = 0
    ) : this(width, height, content, padding, padding, padding, padding)

    override fun measure() {
        content.measure()

        measuredWidth = when (desiredWidth) {
            LayoutSize.WRAP_CONTENT -> content.measuredWidth + paddingLeft + paddingRight
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredWidth.size
        }
        measuredHeight = when (desiredHeight) {
            LayoutSize.WRAP_CONTENT -> content.measuredHeight + paddingTop + paddingBottom
            LayoutSize.MATCH_PARENT -> Int.MAX_VALUE
            else -> desiredHeight.size
        }
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        content.layout(
            paddingLeft,
            paddingTop,
            width - paddingLeft - paddingRight,
            height - paddingTop - paddingBottom
        )
    }

    override fun draw(canvas: Canvas) {
        val saveCount = canvas.save()
        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())
        content.draw(canvas)
        canvas.restoreToCount(saveCount)
    }
}
