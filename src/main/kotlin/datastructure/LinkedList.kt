package datastructure

class Node<T>(var value: T, var next: Node<T>? =  null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}

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

class LinkedList<T>: Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size = 0
        private set

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun toString(): String {
        return if (isEmpty()) {
            "Empty list"
        } else {
            head.toString()
        }
    }

    /**
     * Adds a value at the front of the list
     * Time complexity equals O(1)
     */
    fun push(value: T): LinkedList<T> {
        head = Node(value, head)
        if (tail == null) {
            tail = head
        }
        size++
        return this
    }

    /**
     * Adds a value at the end of the list
     * Time complexity equals O(1)
     */
    fun append(value: T) {
        // if the list is empty, It's need to update both head and tail to the new node,
        // Since we can use push because when the list is empty it's identical to push.
        if (isEmpty()) {
            push(value)
            return
        }
        // Tail will never be null here because you were already handled the case where
        // the list is empty in the if statement.
        tail?.next = Node(value)
        // Since this is tail-end insertion, your new node is also the tail of the list.
        tail = tail?.next
        size++
    }

    /**
     * Adds a value after a particular node of the list
     * Time complexity equals O(1)
     */
    fun insert(value: T, afterNode: Node<T>): Node<T> {
        // In the case where this method is called with the tail node, call the functionality equivalent append method.
        // This takes care of updating tail.
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
        // Otherwise, create a new node and link its next property to the next node of the list
        val newNode = Node(value, afterNode.next)
        // Reassign the next value of the specific node to link it to the new node that you just created.
        afterNode.next = newNode
        size++
        return newNode
    }

    /**
     * Find and get a particular node of the list
     * Time complexity equals O(i) which i is the given index as input parameter
     */
    fun nodeAt(index: Int): Node<T>? {
        // Create a new reference to head and keep track if the current number if traversals.
        var currentNode = head
        var currentIndex = 0
        // Move down the list until reach the desired index. Empty or out-of-bounds indexed will result in a null return value
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    /**
     * Removes the value at the front of the list
     * note: Once the method finished the garbage collector will remove the first value from the memory
     * Time complexity equals O(1)
     */
    fun pop(): T? {
        if(isEmpty().not()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }
        return result
    }

    /**
     * Removes the value at the end of the list
     * Time complexity equals O(n)
     */
    fun removeLast(): T? {
        // if head is null, there is nothing to remove, return null
        val head = head ?: return null
        // if the list only consists of one node, removeLast is functionally equivalent to pop. Since pop will handle
        // updating the head and tail references, so delegate this work to pop method
        if (head.next == null) return pop()
        // update the size of the list cause it's going to remove a node
        size--
        // looking for the next node until current.next is null. it means that the current is the last node of the list
        var prev = head
        var current = head
        var next = current.next
        while(next != null) {
            prev = current
            current = next
            next = current.next
        }
        // since current is the last node, disconnect it using the prev.next reference, and update the tail
        prev.next = null
        tail = prev
        return current.value
    }

    /**
     * Removes a value anywhere in the list
     * Time complexity equals O(1)
     */
    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value

        if (node.next == tail) {
            tail = node
        }

        if (node.next != null) {
            size--
        }

        node.next = node.next?.next
        return result
    }

    /**
     * Kotlin Iterable interface
     */
    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator(this)
    }

    /**
     * Kotlin collection interface
     * This method iterates through all elements of the list if needed
     * Time complexity equals O(n)
     */
    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element) return true
        }
        return false
    }

    /**
     * It's just works with a collection of elements
     * Time complexity equals O(n^2)
     */
    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements) {
            if (!contains(searched)) return false
        }
        return true
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            append(element)
        }
        return true
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while(iterator.hasNext()) {
            val item = iterator.next()

            if (item == element) {
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for(item in elements) {
            result = remove(item) || result
        }
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }

}