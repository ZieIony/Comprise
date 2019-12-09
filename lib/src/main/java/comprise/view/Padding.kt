package comprise.view

class Padding : View {
    var size: Int = 0

    constructor(size: Int) {
        this.size = size
    }

    override fun measure() {
        measuredWidth = size
        measuredHeight = size
    }
}
