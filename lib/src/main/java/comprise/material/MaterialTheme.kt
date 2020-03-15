package comprise.material

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import comprise.theme.dp


class MaterialTheme(context: Context) {
    var colorAccent = 0xffD81B60.toInt()
    var colorPrimary = 0xff008577.toInt()
    var colorPrimaryDark = 0xff00574B.toInt()

    var textColorPrimary = ColorStateList.valueOf(0xff000000.toInt())
    var textColorInverse = ColorStateList.valueOf(0xffffffff.toInt())

    var buttonStyle = MaterialButtonStyle(
        background = ColorDrawable(colorAccent),
        cornerRadius = 2.0f.dp
    )

    var textButtonStyle = MaterialTextButtonStyle(
        background = ColorDrawable(colorAccent),
        cornerRadius = 2.0f.dp,
        textColor = textColorInverse,
        textSize = 14.0f.dp
    )

    val toolbarStyle = ToolbarStyle(
        textColor = textColorInverse,
        background = ColorDrawable(colorPrimary)
    )
}