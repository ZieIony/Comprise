package comprise.widget

class StateList<T>(var defaultValue: T, var values: List<Pair<IntArray, T>> = emptyList()) {
    fun getValue(state: IntArray): T {
        for (pair in values) {
            if (pair.first.all { state.contains(it) })
                return pair.second
        }
        return defaultValue
    }
}
