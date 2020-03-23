package comprise.material

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import comprise.dp
import comprise.widget.StateList
import comprise.widget.TextStyle


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

    var buttonTextStyle = TextStyle(
        textColor = textColorInverse,
        textSize = 14.0f.dp
    )

    var headerTextStyle = TextStyle(
        textColor = textColorInverse,
        textSize = 20.0f.dp
    )

    var textButtonStyle = MaterialTextButtonStyle(
        background = ColorDrawable(colorAccent),
        cornerRadius = 2.0f.dp,
        textStyle = buttonTextStyle
    )

    val toolbarStyle = ToolbarStyle(
        textStyle = headerTextStyle,
        background = ColorDrawable(Color.WHITE)
    )

    val actionBarStyle = ActionBarStyle(
        textStyle = headerTextStyle,
        background = ColorDrawable(colorPrimary),
        elevation = StateList(8.0f.dp)
    )
}