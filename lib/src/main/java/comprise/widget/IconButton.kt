package comprise.widget

import android.graphics.drawable.Drawable
import comprise.view.ContentView
import comprise.view.Ripple

class IconButton : ContentView {
    constructor(drawable: Drawable) : super(content = Ripple(content = Image(drawable = drawable)))
}
