package datastructure.linkedlist

/**
 * A node is like a single element of a chain that responsible to:
 * hold a value
 * hold a reference to the next node(next element of chain) until null which means the end of the list
 * hold a reference to the previous node(previous element of chain)
 */
class Node<T>(var value: T, var next: Node<T>? =  null, var previous: Node<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}