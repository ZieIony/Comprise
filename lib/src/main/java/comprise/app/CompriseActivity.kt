package comprise.app

import androidx.appcompat.app.AppCompatActivity
import comprise.view.View

open class CompriseActivity : AppCompatActivity() {

    fun setContentView(compoundView: View) {
        val compoundLayout = CompriseLayout(this)
        super.setContentView(compoundLayout)
        compoundLayout.views.add(compoundView)
    }

    val Int.dp: Int
        get() = (this * resources.displayMetrics.density).toInt()
    val Int.sp: Int
        get() = (this * resources.displayMetrics.scaledDensity).toInt()

    val Float.dp: Float
        get() = (this * resources.displayMetrics.density)
    val Float.sp: Float
        get() = (this * resources.displayMetrics.scaledDensity)
}
