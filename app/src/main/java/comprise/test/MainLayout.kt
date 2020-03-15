package comprise.test

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import comprise.app.CompriseLayout
import comprise.material.*
import comprise.theme.dp
import comprise.view.LayoutSize
import comprise.view.Padding
import comprise.view.Ripple
import comprise.view.View
import comprise.widget.*

class MainLayout : CompriseLayout {
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val theme = MaterialTheme(context)

        val button = MaterialTextButton(
            style = theme.textButtonStyle,
            background = ColorDrawable(resources.getColor(R.color.colorAccent)),
            text = "TEST BUTTON"
        )
        var clickCount = 0
        button.onClick = {
            clickCount++
            button.text = "CLICKS $clickCount"
            requestLayout()
            invalidate()
        }

        val toolbar = Toolbar(
            style = theme.toolbarStyle,
            icon = IconButton(
                drawable = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
            ),
            title = Text(
                textSize = theme.toolbarStyle.textSize,
                textColor = theme.toolbarStyle.textColor,
                text = "Main activity"
            )
        )

        views.add(
            Column(
                views = listOf(
                    toolbar,
                    Padding(
                        width = LayoutSize.MATCH_PARENT,
                        padding = 16.dp,
                        content = makeContent()
                    ),
                    button,
                    MaterialButton(
                        style = theme.buttonStyle,
                        width = LayoutSize.MATCH_PARENT,
                        content = Text(
                            text = "TEST BUTTON 2",
                            textColor = theme.textButtonStyle.textColor,
                            textSize = theme.textButtonStyle.textSize
                        )
                    ),
                    Button(
                        style = theme.buttonStyle,
                        width = LayoutSize(56.dp),
                        height = LayoutSize(56.dp),
                        content = Text(
                            text = "TEST BUTTON 3",
                            textColor = theme.textButtonStyle.textColor,
                            textSize = theme.textButtonStyle.textSize
                        )
                    )
                )
            )
        )
    }

    private fun makeContent(): View {
        return Card(
            width = LayoutSize.MATCH_PARENT,
            content = Ripple(
                width = LayoutSize.MATCH_PARENT,
                content = Stack(
                    views = listOf(
                        Image(
                            width = LayoutSize.MATCH_PARENT,
                            height = LayoutSize(500),
                            drawable = ColorDrawable(0xff7fffff.toInt())
                        ),
                        Column(
                            views = listOf(
                                Image(
                                    drawable = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
                                ),
                                Text(
                                    text = "test",
                                    textSize = resources.getDimension(R.dimen.comprise_textSize),
                                    textColor = ColorStateList.valueOf(
                                        0xff000000.toInt()
                                    )
                                ),
                                Image(
                                    drawable = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
                                )
                            )
                        )
                    )
                )
            )
        )
    }
}
