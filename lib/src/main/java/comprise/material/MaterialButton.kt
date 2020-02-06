package comprise.material

import android.content.res.ColorStateList
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.text.Layout
import android.view.MotionEvent
import comprise.theme.ButtonStyle
import comprise.theme.dp
import comprise.view.Clip
import comprise.view.ContentView
import comprise.view.LayoutSize
import comprise.widget.Button
import comprise.widget.Shadow
import comprise.widget.Text


open class MaterialButtonStyle(
    textSize: Float,
    textColor: ColorStateList,
    var cornerRadius: Float = 2.0f.dp,
    var minWidth: Int = 0,
    var minHeight: Int = 36.dp
) : ButtonStyle(textSize, textColor)

open class MaterialTextButton(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    style: MaterialButtonStyle,
    background: Drawable? = null,
    textSize: Float? = null,
    textColor: ColorStateList? = null,
    text: CharSequence = "",
    cornerRadius: Float? = null,
    onClick: ((MotionEvent) -> Unit)? = null
) : ContentView(width, height) {

    private val textView = Text(
        width, height,
        style.minWidth, style.minHeight,
        text = text,
        textColor = textColor ?: style.textColor,
        textSize = textSize ?: style.textSize,
        alignment = Layout.Alignment.ALIGN_CENTER
    )

    private val button = Button(width, height, background, textView, onClick)

    var text: CharSequence
        get() = textView.text
        set(value) {
            textView.text = value
        }

    var textSize: Float
        get() = textView.textSize
        set(value) {
            textView.textSize = value
        }

    var textColor: ColorStateList
        get() = textView.textColor
        set(value) {
            textView.textColor = value
        }

    var onClick
        get() = button.onClick
        set(value) {
            button.onClick = value
        }

    var cornerRadius = cornerRadius ?: style.cornerRadius

    private val rect = RectF()
    private val path = Path()

    init {
        this.content = Shadow(
            width, height,
            path = path,
            content = Clip(
                width, height,
                path = path,
                content = button
            )
        )
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        path.reset()
        rect.set(0.0f, 0.0f, width.toFloat(), height.toFloat())
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CCW)
    }
}
