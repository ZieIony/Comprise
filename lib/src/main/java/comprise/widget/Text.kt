package comprise.widget

import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import comprise.view.LayoutSize
import comprise.view.View
import kotlin.math.abs
import kotlin.math.max

class Text(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    minWidth: Int = 0,
    minHeight: Int = 0,
    text: CharSequence = "",
    var textColor: ColorStateList = ColorStateList.valueOf(0xff000000.toInt()),
    textSize: Float = 20.0f,
    var alignment: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL
) : View(width, height, minWidth, minHeight) {

    var text: CharSequence = text
        set(text) {
            field = text
            layout = null
        }

    private var layout: StaticLayout? = null
    private var rect = Rect()
    private var baseline = 0

    val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    var textSize: Float
        get() = paint.textSize
        set(value) {
            paint.textSize = value
        }

    init {
        this.textSize = textSize
    }

    fun setTypeface(typeface: Typeface) {
        paint.typeface = typeface
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        layout = null
    }

    override fun draw(canvas: Canvas) {
        if (layout == null) {
            val alignment: Layout.Alignment = alignment
            layout = StaticLayout(
                text, paint, width, alignment, 1.0f, 0.0f, false
            )
        }

        val saveCount = canvas.save()
        paint.color = textColor.defaultColor
        layout!!.draw(canvas)
        canvas.restoreToCount(saveCount)
    }

    override fun measure() {
        measuredWidth = 0
        measuredHeight = 0

        layout = StaticLayout(
            this.text, paint, Integer.MAX_VALUE, alignment, 1.0f, 0.0f, false
        )
        var maxWidth = 0
        for (i in 0 until layout!!.lineCount)
            maxWidth = max(maxWidth.toFloat(), layout!!.getLineWidth(i)).toInt()
        measuredWidth += maxWidth
        measuredWidth = max(measuredWidth, minWidth)

        layout = StaticLayout(
            this.text, paint,
            measuredWidth, alignment, 1.0f, 0.0f, false
        )
        measuredHeight += layout!!.height
        measuredHeight = max(measuredHeight, minHeight)

        val firstLine = this.text.subSequence(0, layout!!.getLineEnd(0)).toString()
        paint.getTextBounds(firstLine, 0, firstLine.length, rect)
        baseline = abs(rect.top)
    }

}
