package datastructure.stack

class StackImp<T>: Stack<T> {

    private val storage = arrayListOf<T>()

    /**
     * Adding an element to the top of the stack.
     */
    override fun push(element: T) {
        storage.add(element)
    }

    /**
     * Removing the top element of the stack
     */
    override fun pop(): T? {
        if (storage.size == 0) return null
        return storage.removeAt(storage.size - 1)
    }

    override fun toString(): String  {
        return storage.asReversed().toString()
    }

}