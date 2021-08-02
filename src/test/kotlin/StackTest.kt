import datastructure.stack.StackImp
import org.junit.Test
import kotlin.test.assertEquals

class StackTest {

    @Test
    fun `print stack`(){
        val stack = StackImp<Int>().apply {
            push(1)
            push(2)
            push(3)
            push(4)
        }
        assertEquals("[4, 3, 2, 1]", stack.toString())
        println(stack)
        val poppedElement = stack.pop()
        if (poppedElement != null) {
            println("Popped: $poppedElement")
        }
        assertEquals("[3, 2, 1]", stack.toString())
        println(stack)
    }

    @Test
    fun `initializing a stack from an array literal`() {
        val stack = StackImp.stackOf(1, 2, 3, 4)
        assertEquals("[4, 3, 2, 1]", stack.toString())
        stack.pop()
        assertEquals("[3, 2, 1]", stack.toString())
    }

}