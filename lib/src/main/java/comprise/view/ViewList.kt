package comprise.view


class ViewList(private val owner: View) : MutableList<View> {

    private val views = mutableListOf<View>()

    override val size: Int
        get() = views.size

    override fun contains(element: View) = views.contains(element)

    override fun containsAll(elements: Collection<View>) = views.containsAll(elements)

    override fun get(index: Int) = views.get(index)

    override fun indexOf(element: View) = views.indexOf(element)

    override fun isEmpty() = views.isEmpty()

    override fun lastIndexOf(element: View) = views.lastIndexOf(element)

    override fun add(element: View): Boolean {
        element.parent = owner
        return views.add(element)
    }

    override fun add(index: Int, element: View) {
        element.parent = owner
        views.add(index, element)
    }

    override fun addAll(index: Int, elements: Collection<View>): Boolean {
        elements.forEach { it.parent = owner }
        return views.addAll(index, elements)
    }

    override fun addAll(elements: Collection<View>): Boolean {
        elements.forEach { it.parent = owner }
        return views.addAll(elements)
    }

    override fun clear() {
        views.forEach { it.parent = null }
        views.clear()
    }

    override fun iterator() = ViewListIterator()

    override fun listIterator() = ViewListIterator()

    override fun listIterator(index: Int) = ViewListIterator(index)

    override fun remove(element: View): Boolean {
        element.parent = null
        return views.remove(element)
    }

    override fun removeAll(elements: Collection<View>): Boolean {
        TODO("not implemented")
    }

    override fun removeAt(index: Int): View {
        views[index].parent = null
        return views.removeAt(index)
    }

    override fun retainAll(elements: Collection<View>): Boolean {
        TODO("not implemented")
    }

    override fun set(index: Int, element: View): View {
        views[index].parent = null
        element.parent = owner
        return views.set(index, element)
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<View> = this

    inner class ViewListIterator(private var index: Int = 0) : MutableListIterator<View> {

        override fun hasNext() = size > index

        override fun next() = get(index++)

        override fun remove() {
            removeAt(index)
        }

        override fun hasPrevious() = index > 0

        override fun nextIndex() = index + 1

        override fun previous() = get(index--)

        override fun previousIndex() = index - 1

        override fun add(element: View) {
            add(index, element)
        }

        override fun set(element: View) {
            set(index, element)
        }
    }
}