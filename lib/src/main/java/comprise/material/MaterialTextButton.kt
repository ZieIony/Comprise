package comprise.material

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.text.Layout
import android.view.MotionEvent
import comprise.theme.dp
import comprise.view.LayoutSize
import comprise.widget.Text


open class MaterialTextButtonStyle(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    minWidth: Int = 0,
    minHeight: Int = 36.dp,
    background: Drawable?,
    cornerRadius: Float = 2.0f.dp,
    var textSize: Float,
    var textColor: ColorStateList
) : MaterialButtonStyle(width, height, minWidth, minHeight, background, cornerRadius)

open class MaterialTextButton(
    style: MaterialTextButtonStyle,
    width: LayoutSize = style.width,
    height: LayoutSize = style.height,
    background: Drawable? = style.background,
    textSize: Float = style.textSize,
    textColor: ColorStateList = style.textColor,
    text: CharSequence = "",
    cornerRadius: Float = style.cornerRadius,
    onClick: ((MotionEvent) -> Unit)? = null
) : MaterialButton(
    style,
    width, height,
    background,
    cornerRadius = cornerRadius,
    onClick = onClick
) {

    private val textView = Text(
        this.desiredWidth, this.desiredHeight,
        style.minWidth, style.minHeight,
        text = text,
        textColor = textColor,
        textSize = textSize,
        alignment = Layout.Alignment.ALIGN_CENTER
    )

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

    init {
        content = textView
    }
}