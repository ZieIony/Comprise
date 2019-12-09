package comprise.widget

import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import comprise.view.Clip
import comprise.view.ContentView

class Button : ContentView {

    private val image:Image
    private val text:Text

    constructor(drawable: Drawable?, text:CharSequence) : super(content = Stack()) {
        image = Image(drawable=drawable)
        this.text = Text(text=text)
        (content as Stack).views.addAll(listOf(
            Clip(
                path= Path().also { it.addRoundRect(RectF(0.0f,0.0f,200.0f,50.0f),5.0f,5.0f,Path.Direction.CW) },
                content=Stack(views= listOf(
                image,
                this.text
            )))
        ))
    }
}
