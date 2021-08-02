package datastructure.queue

class RingBufferQueue<T>(size: Int): Queue<T> {

    private val ringBuffer: RingBuffer<T> = RingBuffer(size)

    override val count: Int
        get() = ringBuffer.count

    /**
     * time complexity for this operation is O(1)
     */
    override fun peek(): T? {
        return ringBuffer.first
    }

    /**
     * time complexity for this operation is O(1)
     */
    override fun enqueue(element: T): Boolean {
        return ringBuffer.write(element)
    }

    override fun dequeue(): T? {
        return if (isEmpty) {
            null
        } else {
            ringBuffer.read()
        }
    }

    override fun toString(): String {
        return ringBuffer.toString()
    }

}