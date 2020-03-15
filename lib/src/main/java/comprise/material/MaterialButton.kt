package comprise.material

import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import comprise.theme.ButtonStyle
import comprise.theme.dp
import comprise.view.*
import comprise.widget.Button
import comprise.widget.Shadow


open class MaterialButtonStyle(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    minWidth: Int = 0,
    minHeight: Int = 36.dp,
    background: Drawable?,
    var cornerRadius: Float = 2.0f.dp
) : ButtonStyle(width, height, minWidth, minHeight, background)

open class MaterialButton(
    style: MaterialButtonStyle,
    width: LayoutSize = style.width,
    height: LayoutSize = style.height,
    background: Drawable? = style.background,
    content: View? = null,
    var cornerRadius: Float = style.cornerRadius,
    onClick: ((MotionEvent) -> Unit)? = null
) : ContentView(width, height) {

    private val ripple = Ripple(width, height)

    init {
        content?.let{
            this.content = it
        }
    }

    override var content: View
        get() = ripple.content
        set(value) {
            ripple.content = value
        }

    private val button: Button = Button(
        style,
        width, height,
        background,
        ripple,
        onClick
    )

    var onClick
        get() = button.onClick
        set(value) {
            button.onClick = value
        }

    private val rect = RectF()
    private val path = Path()

    init {
        super.content = Shadow(
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

