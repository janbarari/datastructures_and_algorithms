package datastructure.queue

import datastructure.linkedlist.DoublyLinkedList

class LinkedListQueue<T>: Queue<T> {
    private val list = DoublyLinkedList<T>()
    private var size = 0
    override val count: Int
        get() = size

    /**
     * time complexity of this operation is O(1)
     */
    override fun enqueue(element: T): Boolean {
        list.append(element)
        size++
        return true
    }

    /**
     * time complexity of this operation is O(1)
     * this is much better approach than arraylist because you didn't have to shift the elements one by one
     */
    override fun dequeue(): T? {
        val firstNode = list.first ?: return null
        size--
        return list.remove(firstNode)
    }

    override fun peek(): T? {
        return list.first?.value
    }

    override fun toString(): String {
        return list.toString()
    }
}