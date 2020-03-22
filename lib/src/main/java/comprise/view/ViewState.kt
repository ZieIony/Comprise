package comprise.view

class ViewState(var owner: View) {
    private var states = mutableSetOf<Int>()

    fun add(state: Int) {
        if (states.add(state))
            owner.onStateChanged()
    }

    fun get(): Set<Int> = states

    fun clear(state: Int) {
        if (states.remove(state))
            owner.onStateChanged()
    }
}
