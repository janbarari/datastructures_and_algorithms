import datastructure.tree.TreeNode
import org.junit.Test

class TreeTest {

    @Test
    fun `test print by level is success`() {
        val root = TreeNode(15).apply {
            add(
                TreeNode(1).apply {
                    add(TreeNode(1))
                    add(TreeNode(5))
                    add(TreeNode(0))
                }
            )
            add(
                TreeNode(17).apply {
                    add(TreeNode(2))
                }
            )
            add(
                TreeNode(20).apply {
                    add(TreeNode(5))
                    add(TreeNode(7))
                }
            )
        }

        root.printLevels()
    }

}