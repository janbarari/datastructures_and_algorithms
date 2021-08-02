import datastructure.queue.ArrayListQueue
import org.junit.Test
import kotlin.test.assertEquals

class ArrayListQueueTest {

    @Test
    fun `queue with array list`() {
        val queue = ArrayListQueue<String>().apply {
            enqueue("Mehdi")
            enqueue("Baba")
            enqueue("Maman")
        }
        println(queue)
        assertEquals("[Mehdi, Baba, Maman]", queue.toString())

        println(queue.peek())
        assertEquals("Mehdi", queue.peek())

        queue.dequeue()
        println(queue)
        assertEquals("[Baba, Maman]", queue.toString())
    }

}