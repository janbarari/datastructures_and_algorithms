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

class LinkedList<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    fun isEmpty(): Boolean {
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

}

fun main() {
    println("LinkedList")

    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)

    println("Before inserting: $list")
    var middleNode = list.nodeAt(1)!!
    for (i in 1..3) {
        middleNode = list.insert(-1 * i, middleNode)
    }
    println("After inserting: $list")

}