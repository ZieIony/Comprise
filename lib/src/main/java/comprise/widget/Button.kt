package comprise.widget

import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import comprise.view.Clip
import comprise.view.ContentView
import comprise.view.LayoutSize
import comprise.view.Padding

class Button(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    background: Drawable? = null,
    drawable: Drawable? = null,
    text: CharSequence
) : ContentView() {

    private val rect = RectF()
    private val path = Path()

    private val backgroundImage = Image(
        LayoutSize.MATCH_PARENT,
        LayoutSize.MATCH_PARENT,
        drawable = background
    )

    private val image = Image(width, height, drawable = drawable)

    private val text = Text(width, height, text = text, textSize = 14.0f * 3)

    init {
        content = Shadow(
            width, height,
            path = path,
            content = Clip(
                width, height,
                path = path,
                content = Stack(
                    width, height,
                    views = listOf(
                        backgroundImage,
                        Padding(
                            width, height,
                            padding = 8 * 3,
                            content = Stack(
                                width, height,
                                views = listOf(
                                    image,
                                    this.text
                                )
                            )
                        )
                    )
                )
            )
        )
    }

    override fun layout(x: Int, y: Int, width: Int, height: Int) {
        super.layout(x, y, width, height)
        path.reset()
        rect.set(0.0f, 0.0f, width.toFloat(), height.toFloat())
        path.addRoundRect(rect, (2 * 3).toFloat(), (2 * 3).toFloat(), Path.Direction.CCW)
    }
}
