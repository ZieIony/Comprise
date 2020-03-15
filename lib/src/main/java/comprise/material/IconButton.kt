package comprise.material

import android.graphics.drawable.Drawable
import comprise.theme.dp
import comprise.view.ContentView
import comprise.view.LayoutSize
import comprise.view.Padding
import comprise.view.Ripple
import comprise.widget.Image

class IconButton(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    drawable: Drawable
) : ContentView(
    width, height,
    content = Ripple(
        content = Padding(
            padding = 16.dp,
            content = Image(
                width = LayoutSize(24.dp),
                height = LayoutSize(24.dp),
                drawable = drawable
            )
        )
    )
)