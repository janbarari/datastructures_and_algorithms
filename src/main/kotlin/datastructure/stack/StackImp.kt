package datastructure.stack

class StackImp<T>: Stack<T> {

    companion object {

        fun <T> create(items: Iterable<T>): Stack<T> {
            val stack = StackImp<T>()
            for (item in items) {
                stack.push(item)
            }
            return stack
        }

        fun <T> stackOf(vararg elements: T): Stack<T> {
            return StackImp.create(elements.asList())
        }

    }

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

    /**
     * Get the top element of the stack
     */
    override fun peek(): T? {
        return storage.lastOrNull()
    }

    override val count: Int
        get() = storage.size

}