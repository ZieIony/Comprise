package comprise.material

import comprise.view.LayoutSize

open class Style(
    var width: LayoutSize = LayoutSize.WRAP_CONTENT,
    var height: LayoutSize = LayoutSize.WRAP_CONTENT,
    var minWidth: Int = 0,
    var minHeight: Int = 0
)