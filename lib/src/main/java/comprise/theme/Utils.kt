package comprise.theme

import android.content.res.Resources


val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)