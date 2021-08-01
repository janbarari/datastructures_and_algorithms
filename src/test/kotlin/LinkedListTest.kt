import datastructure.linkedlist.LinkedList
import org.junit.Test
import kotlin.test.assertEquals

class LinkedListTest {

    @Test
    fun `check nodes value`() {
        val list = LinkedList<Int>().apply {
            add(3)
            add(2)
            add(1)
            add(4)
            add(5)
        }
        assertEquals("3 -> 2 -> 1 -> 4 -> 5", list.toString())
    }

}