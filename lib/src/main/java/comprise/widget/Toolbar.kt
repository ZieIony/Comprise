package comprise.widget

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import comprise.view.ContentView
import comprise.view.LayoutSize

class Toolbar : ContentView, ToolbarStyle {
 override   var backgroundColor: Int

    constructor(
        style: ToolbarStyle,
        icon: Drawable,
        text: CharSequence
    ) : super(style) {
        content = Stack(
            views = listOf(
                Image(
                    drawable = ColorDrawable(style.backgroundColor)
                ),
                Row(
                    views = listOf(
                        IconButton(drawable = icon),
                        Text(text = text)
                    )
                )
            )
        )

        this.backgroundColor = style.backgroundColor
    }
}

interface ToolbarStyle : ViewStyle {
    var backgroundColor: Int
}

interface ViewStyle {
    var desiredWidth: LayoutSize
    var desiredHeight: LayoutSize
}
