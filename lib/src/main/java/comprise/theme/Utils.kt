package comprise.theme

import android.content.res.Resources


val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
val Int.sp: Int
    get() = (this * Resources.getSystem().displayMetrics.scaledDensity).toInt()

val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)
val Float.sp: Float
    get() = (this * Resources.getSystem().displayMetrics.scaledDensity)