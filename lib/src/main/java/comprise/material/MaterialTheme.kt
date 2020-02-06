package comprise.material

import android.content.Context
import android.content.res.ColorStateList


class MaterialTheme(context: Context) {
    var textColorPrimary = ColorStateList.valueOf(0xff000000.toInt())
    var textColorInverse = ColorStateList.valueOf(0xffffffff.toInt())
    var buttonStyle = MaterialButtonStyle(
        textColor = textColorInverse,
        textSize = 14.0f * 3,
        cornerRadius = 2.0f * 3
    )
}