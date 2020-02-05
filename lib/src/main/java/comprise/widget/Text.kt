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

class Text : View {

    var text: CharSequence?
        set(text) {
            field = text
            layout = null
        }

    var textColor: ColorStateList
    private var layout: StaticLayout? = null
    internal var rect = Rect()
    private var baseline = 0

    val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    constructor(
        width: LayoutSize = LayoutSize.WRAP_CONTENT,
        height: LayoutSize = LayoutSize.WRAP_CONTENT,
        text: CharSequence = "",
        textColor: ColorStateList = ColorStateList.valueOf(0xff000000.toInt()),
        textSize: Float = 20.0f
    ) : super(width, height) {
        this.text = text
        this.textColor = textColor
        setTextSize(textSize)
    }

    fun setTextSize(textSize: Float) {
        paint.textSize = textSize
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
            val alignment: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL
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
            this.text, paint, Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false
        )
        var maxWidth = 0
        for (i in 0 until layout!!.lineCount)
            maxWidth = max(maxWidth.toFloat(), layout!!.getLineWidth(i)).toInt()
        measuredWidth += maxWidth

        layout = StaticLayout(
            this.text, paint,
            measuredWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false
        )
        measuredHeight += layout!!.height

        val firstLine = this.text!!.subSequence(0, layout!!.getLineEnd(0)).toString()
        paint.getTextBounds(firstLine, 0, firstLine.length, rect)
        baseline = abs(rect.top)
    }

}
