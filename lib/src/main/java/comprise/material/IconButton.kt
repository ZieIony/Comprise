package comprise.material

import android.graphics.drawable.Drawable
import comprise.view.ContentView
import comprise.view.LayoutSize
import comprise.view.Ripple
import comprise.widget.HorizontalGravity
import comprise.widget.Image
import comprise.widget.Stack
import comprise.widget.VerticalGravity

class IconButton(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    drawable: Drawable
) : ContentView(
    width, height,
    content = Ripple(
        content = Stack(
            horizontalGravity = HorizontalGravity.CENTER,
            verticalGravity = VerticalGravity.CENTER,
            views = listOf(
                Image(
                    width = LayoutSize(24 * 3),
                    height = LayoutSize(24 * 3),
                    drawable = drawable
                )
            )
        )
    )
)