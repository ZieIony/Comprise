package comprise.material

import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import comprise.dp
import comprise.view.*
import comprise.widget.ImageView
import comprise.widget.Stack
import comprise.widget.TextStyle
import comprise.widget.VerticalGravity


open class ToolbarStyle(
    var textStyle: TextStyle,
    var background: Drawable? = null
) : Style(
    width = LayoutSize.MATCH_PARENT,
    minHeight = 56.dp
)

class Toolbar(
    style: ToolbarStyle,
    width: LayoutSize = style.width,
    height: LayoutSize = style.height,
    minWidth: Int = style.minWidth,
    minHeight: Int = style.minHeight,
    name: String? = null,
    icon: View? = null,
    title: View? = null,
    background: Drawable? = style.background
) : ViewContainer(width, height, minWidth, minHeight, name = name) {

    private val rect = RectF()
    private val path = Path()

    private val backgroundImage = ImageView(
        LayoutSize.MATCH_PARENT, LayoutSize.MATCH_PARENT, drawable = background
    )

    var background by ChildPropertDelegate<Drawable?>(backgroundImage::drawable)

    init {
        child = Clip(
            width, height,
            child = Stack(
                width, height,
                verticalGravity = VerticalGravity.CENTER,
                children = if (icon != null) {
                    listOf(
                        backgroundImage,
                        Padding(paddingLeft = 4.dp, child = icon),
                        Padding(paddingLeft = 72.dp, child = title)
                    )
                } else {
                    listOf(
                        backgroundImage,
                        Padding(paddingLeft = 16.dp, child = title)
                    )
                }
            ),
            path = path
        )
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        path.reset()
        rect.set(0.0f, 0.0f, width.toFloat(), height.toFloat())
        path.addRect(rect, Path.Direction.CCW)
    }
}