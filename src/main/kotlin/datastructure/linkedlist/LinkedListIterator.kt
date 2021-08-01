package datastructure.linkedlist

class LinkedListIterator<T>(
    private val list: LinkedList<T>
): Iterator<T>, MutableIterator<T> {

    private var index = 0
    private var lastNode: Node<T>? = null

    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun next(): T {
        // Check there are still elements to return. if there aren't then throw an exception
        if (index >= list.size) throw IndexOutOfBoundsException()
        // If this is the first iteration there is no lastNode set, so take the first node of the list,
        // after the first iteration, get the next property of the last node to find the next one.
        lastNode = if (index == 0) {
            list.nodeAt(0)
        } else {
            lastNode?.next
        }
        // Since the lastNode property was updated, try to update index too
        index++
        return lastNode!!.value
    }

    override fun remove() {
        // The simplest case is when the beginning of the list. Using pop() will do the trick.
        if (index == 1) {
            list.pop()
        } else {
            // If the iterator is somewhere inside the list, its need to find the previous node.
            // That's the only way to remove items from a linked list.
            val prevNode = list.nodeAt(index - 2) ?: return
            // The iterator needs to step back so that next() returns the correct method the next time.
            // This means reassigning the lastNode and decreasing the index.
            list.removeAfter(prevNode)
            lastNode = prevNode
        }
        index--
    }
}
