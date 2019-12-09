package comprise.test

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import comprise.app.CompriseLayout
import comprise.view.LayoutSize
import comprise.widget.*

class MainLayout : CompriseLayout {
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        views.add(
            Column(
                views = listOf(
                    Toolbar(
                        style = ToolbarStyle(),
                      //  height = LayoutSize(resources.getDimensionPixelSize(R.dimen.comprise_toolbarHeight)),
                   //     backgroundColor = resources.getColor(R.color.colorPrimary),
                        icon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp),
                        text = "Main activity"
                    ),
                    Stack(
                        views = listOf(
                            Image(
                                width = LayoutSize.MATCH_PARENT,
                                height = LayoutSize(500),
                                drawable = ColorDrawable(0xff7fffff.toInt())
                            ),
                            Column(
                                views = listOf(
                                    Image(
                                        drawable = resources.getDrawable(R.drawable.ic_launcher_foreground)
                                    ),
                                    Text(
                                        text = "test",
                                        textSize = resources.getDimension(R.dimen.comprise_textSize),
                                        textColor = ColorStateList.valueOf(
                                            0xff000000.toInt()
                                        )
                                    ),
                                    Image(
                                        drawable = resources.getDrawable(R.drawable.ic_launcher_foreground)
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )
    }
}
