package comprise.app

import comprise.view.LayoutSize
import comprise.widget.Stack


class RootLayout(
    width: LayoutSize = LayoutSize.WRAP_CONTENT,
    height: LayoutSize = LayoutSize.WRAP_CONTENT,
    var androidView: android.view.View
) : Stack(width, height) {

    override fun requestLayout() {
        androidView.requestLayout()
    }

    override fun requestDraw() {
        androidView.postInvalidate()
    }
}