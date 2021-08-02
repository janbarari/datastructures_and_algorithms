import datastructure.queue.LinkedListQueue
import org.junit.Test
import kotlin.test.assertEquals

class LinkedListQueueTest {

    @Test
    fun `queue with linked list`() {
        val queue = LinkedListQueue<String>().apply {
            enqueue("Mehdi")
            enqueue("Negar")
            enqueue("Sebastian")
        }

        println(queue)
        assertEquals("Mehdi -> Negar -> Sebastian", queue.toString())

        queue.dequeue()
        assertEquals("Negar -> Sebastian", queue.toString())

    }

}