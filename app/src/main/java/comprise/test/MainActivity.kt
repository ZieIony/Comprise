package comprise.test

import android.os.Bundle
import comprise.app.CompriseActivity

class MainActivity : CompriseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(MainLayout(this))
    }
}

