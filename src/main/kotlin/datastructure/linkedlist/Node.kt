package datastructure.linkedlist

/**
 * A node is like a single element of a chain that responsible to:
 * hold a value
 * hold a reference to the next node(next element of chain) until null which means the end of the list
 */
class Node<T>(var value: T, var next: Node<T>? =  null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}