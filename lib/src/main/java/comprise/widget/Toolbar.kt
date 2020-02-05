package comprise.widget

import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import comprise.view.ContentView
import comprise.view.LayoutSize
import comprise.view.Padding

class Toolbar(
    width: LayoutSize = LayoutSize.MATCH_PARENT,
    height: LayoutSize = LayoutSize(56 * 3),
    icon: Drawable,
    text: CharSequence,
    background: Drawable? = null
) : ContentView(width, height) {

    private val rect = RectF()
    private val path = Path()

    var background: Drawable?
        get() = backgroundImage.drawable
        set(value) {
            backgroundImage.drawable = value
        }

    private val backgroundImage: Image = Image(
        LayoutSize.MATCH_PARENT, LayoutSize.MATCH_PARENT, drawable = background
    )

    private val iconButton: IconButton = IconButton(
        width = LayoutSize(40 * 3),
        height = LayoutSize(40 * 3),
        drawable = icon
    )

    private val titleText: Text = Text(text = text, textSize = 20.0f * 3)

    init {
        content = Shadow(
            radius = 8.0f * 3,
            path = path,
            content = Stack(
                width, height,
                verticalGravity = VerticalGravity.CENTER,
                views = listOf(
                    backgroundImage,
                    Padding(paddingLeft = 16 * 3, content = iconButton),
                    Padding(paddingLeft = 72 * 3, content = titleText)
                )
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