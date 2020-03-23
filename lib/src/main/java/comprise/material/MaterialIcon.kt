package comprise.material

import android.graphics.drawable.Drawable
import comprise.dp
import comprise.view.LayoutSize
import comprise.widget.ImageView

open class MaterialIcon(
    width: LayoutSize = LayoutSize(24.dp),
    height: LayoutSize = LayoutSize(24.dp),
    drawable: Drawable
) : ImageView(width, height, drawable)