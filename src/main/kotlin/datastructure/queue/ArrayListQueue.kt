package datastructure.queue

class ArrayListQueue<T> : Queue<T> {

  private val list = arrayListOf<T>()

  override val count: Int
    get() = list.size

  /**
   * Inserts an element at the back of the queue and returns true if the operation is successful.
   * regarding to the empty space at the back of the ArrayList time complexity of this operation is O(1)
   */
  override fun enqueue(element: T): Boolean {
    list.add(element)
    return true
  }

  /**
   * Removes the element at the front of the queue and returns it.
   * removing an element from the front of the queue is an O(n) time complexity.
   * @return the removed element or null
   */
  override fun dequeue(): T? {
    return if (isEmpty) null else list.removeAt(0)
  }

  /**
   * Returns the element at the front of the queue without removing it.
   */
  override fun peek(): T? = list.getOrNull(0)

  override fun toString(): String {
    return list.toString()
  }

}