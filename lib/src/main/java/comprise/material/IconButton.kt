package comprise.material

import android.graphics.drawable.Drawable
import comprise.dp
import comprise.view.LayoutSize
import comprise.view.Padding
import comprise.view.Ripple
import comprise.view.ViewContainer
import comprise.widget.ImageView

class IconButton(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    drawable: Drawable
) : ViewContainer(
    width, height,
    child = Ripple(
        child = Padding(
            padding = 16.dp,
            child = ImageView(
                width = LayoutSize(24.dp),
                height = LayoutSize(24.dp),
                drawable = drawable
            )
        )
    )
)