package comprise.material

import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import comprise.theme.dp
import comprise.view.LayoutSize
import comprise.view.View
import comprise.view.ViewContainer
import comprise.widget.Shadow
import comprise.widget.StateList
import comprise.widget.TextStyle


class ActionBarStyle(
    textStyle: TextStyle,
    background: Drawable? = null,
    var elevation: StateList<Float> = StateList(8.0f.dp)
) : ToolbarStyle(
    textStyle, background
)

class ActionBar(
    style: ActionBarStyle,
    width: LayoutSize = style.width,
    height: LayoutSize = style.height,
    minWidth: Int = style.minWidth,
    minHeight: Int = style.minHeight,
    name: String? = null,
    icon: View? = null,
    title: View? = null,
    background: Drawable? = style.background,
    elevation: StateList<Float> = style.elevation
) : ViewContainer(width, height, minWidth, minHeight, name) {

    private val rect = RectF()
    private val path = Path()

    private val toolbar = Toolbar(
        style,
        width, height,
        minWidth, minHeight,
        name,
        icon, title, background
    )

    private val shadow = Shadow(
        width, height,
        toolbar,
        path, elevation
    )

    var background by ChildPropertDelegate<Drawable?>(toolbar::background)

    var elevation by ChildPropertDelegate<Drawable?>(shadow::elevation)

    init {
        child = shadow
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        path.reset()
        rect.set(0.0f, 0.0f, width.toFloat(), height.toFloat())
        path.addRect(rect, Path.Direction.CCW)
    }
}