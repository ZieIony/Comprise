package comprise.app

import androidx.appcompat.app.AppCompatActivity
import comprise.view.View

open class CompriseActivity : AppCompatActivity() {

    fun setContentView(compoundView: View) {
        val compoundLayout = CompriseLayout(this)
        super.setContentView(compoundLayout)
        compoundLayout.views.add(compoundView)
    }

}
