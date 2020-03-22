package comprise.material

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.text.Layout
import android.view.MotionEvent
import comprise.theme.dp
import comprise.view.LayoutSize
import comprise.widget.TextView
import comprise.widget.TextStyle


open class MaterialTextButtonStyle(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    minWidth: Int = 0,
    minHeight: Int = 36.dp,
    background: Drawable?,
    cornerRadius: Float = 2.0f.dp,
    var textStyle: TextStyle
) : MaterialButtonStyle(width, height, minWidth, minHeight, background, cornerRadius)

open class MaterialTextButton(
    style: MaterialTextButtonStyle,
    width: LayoutSize = style.width,
    height: LayoutSize = style.height,
    minWidth: Int = 0,
    minHeight: Int = 0,
    name: String? = null,
    background: Drawable? = style.background,
    textSize: Float = style.textStyle.textSize,
    textColor: ColorStateList = style.textStyle.textColor,
    text: CharSequence = "",
    cornerRadius: Float = style.cornerRadius,
    onClick: ((MotionEvent) -> Unit)? = null
) : MaterialButton(
    style,
    width, height,
    minWidth, minHeight,
    name,
    background = background,
    cornerRadius = cornerRadius,
    onClick = onClick
) {

    private val textView = TextView(
        style = style.textStyle,
        width = this.desiredWidth, height = this.desiredHeight,
        minWidth = style.minWidth, minHeight = style.minHeight,
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