package comprise.material

import android.content.res.ColorStateList
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import comprise.theme.dp
import comprise.theme.sp
import comprise.view.*
import comprise.widget.Image
import comprise.widget.Shadow
import comprise.widget.Stack
import comprise.widget.VerticalGravity


class ToolbarStyle(
    var textSize: Float = 20.0f.sp,
    var textColor: ColorStateList,
    var background: Drawable? = null
) : Style(
    width = LayoutSize.MATCH_PARENT,
    minHeight = 56.dp
)

class Toolbar(
    style: ToolbarStyle,
    width: LayoutSize = style.width,
    height: LayoutSize = style.height,
    icon: View?,
    title: View,
    background: Drawable? = style.background
) : ContentView(width, height) {

    private val rect = RectF()
    private val path = Path()

    var background: Drawable?
        get() = backgroundImage.drawable
        set(value) {
            backgroundImage.drawable = value
        }

    private val backgroundImage: Image =
        Image(
            LayoutSize.MATCH_PARENT, LayoutSize.MATCH_PARENT, drawable = background
        )

    init {
        content = Shadow(
            radius = 8.0f * 3,
            path = path,
            content = Clip(
                width, height,
                content = Stack(
                    width, height,
                    verticalGravity = VerticalGravity.CENTER,
                    views = if (icon != null) {
                        listOf(
                            backgroundImage,
                            Padding(paddingLeft = 4.dp, content = icon),
                            Padding(paddingLeft = 72.dp, content = title)
                        )
                    } else {
                        listOf(
                            backgroundImage,
                            Padding(paddingLeft = 16.dp, content = title)
                        )
                    }
                ),
                path = path
            )
        )
    }


    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        path.reset()
        rect.set(0.0f, 0.0f, width.toFloat(), height.toFloat())
        path.addRect(rect, Path.Direction.CCW)
    }
}