import datastructure.queue.RingBufferQueue
import org.junit.Test
import kotlin.test.assertEquals

class RingBufferQueueTest {

    @Test
    fun `queue with ring buffer`() {
        val queue = RingBufferQueue<Int>(4).apply {
            enqueue(1)
            enqueue(2)
            enqueue(3)
            enqueue(4)
            //because the size is 4, the 5 & 6 should not be added in the queue
            enqueue(5)
            enqueue(6)
        }

        print(queue)
        assertEquals("[1, 2, 3, 4]", queue.toString())
        print(" Passed 1/2")
        println()
        queue.dequeue()
        print(queue)
        assertEquals("[2, 3, 4]", queue.toString())
        print(" Passed 2/2")
        println()
    }

}