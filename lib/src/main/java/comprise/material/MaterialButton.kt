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
    minWidth: Int = style.minWidth,
    minHeight: Int = style.minHeight,
    name: String? = null,
    content: View? = null,
    background: Drawable? = style.background,
    var cornerRadius: Float = style.cornerRadius,
    onClick: ((MotionEvent) -> Unit)? = null
) : ViewContainer(width, height, minWidth, minHeight, name), ContentView {

    private val ripple = Ripple(width, height, content)

    override var content by ChildPropertDelegate<View?>(ripple::child)

    private val button: Button = Button(
        style,
        width, height,
        minWidth, minHeight,
        background = background,
        content = ripple,
        onClick = onClick
    )

    var onClick by ChildPropertDelegate<((MotionEvent) -> Unit)?>(button::onClick)

    private val rect = RectF()
    private val path = Path()

    init {
        child = Shadow(
            width, height,
            path = path,
            child = Clip(
                width, height,
                path = path,
                child = button
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

