package comprise.theme

import android.graphics.drawable.Drawable
import comprise.material.Style
import comprise.view.LayoutSize


open class ButtonStyle(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    minWidth: Int = 0,
    minHeight: Int = 0,
    val background: Drawable? = null
) : Style(width, height, minWidth, minHeight)
