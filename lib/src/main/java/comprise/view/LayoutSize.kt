package comprise.view

class LayoutSize {
    val size: Int

    constructor(size: Int = 0) {
        this.size = size
    }

    companion object {
        val WRAP_CONTENT = LayoutSize(-1)
        val MATCH_PARENT = LayoutSize(-2)
    }
}
