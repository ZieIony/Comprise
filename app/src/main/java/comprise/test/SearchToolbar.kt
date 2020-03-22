package comprise.test

import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import comprise.material.IconButton
import comprise.material.Toolbar
import comprise.material.ToolbarStyle
import comprise.theme.dp
import comprise.view.Clip
import comprise.view.LayoutSize
import comprise.view.ViewContainer
import comprise.widget.Shadow
import comprise.widget.TextView
import comprise.widget.TextStyle


class SearchToolbarStyle(
    textStyle: TextStyle, background: Drawable?,
    var cornerRadius: Float = 2.0f.dp
) : ToolbarStyle(textStyle, background)

class SearchToolbar(
    style: SearchToolbarStyle,
    width: LayoutSize = style.width,
    height: LayoutSize = style.height,
    minWidth: Int = style.minWidth,
    minHeight: Int = style.minHeight,
    name: String?,
    icon: Drawable,
    hint: String,
    var cornerRadius: Float = style.cornerRadius
) : ViewContainer(width, height, minWidth, minHeight, name) {

    private val rect = RectF()
    private val path = Path()

    init {
        child = Shadow(
            path = path,
            child = Clip(
                path = path,
                child = Toolbar(
                    style = style,
                    icon = IconButton(
                        drawable = icon
                    ),
                    title = TextView(
                        style = style.textStyle,
                        text = hint
                    )
                )
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