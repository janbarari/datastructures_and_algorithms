package datastructure.tree

import list.ArrayListQueue

/**
 * The tree is a data structure of profound importance. It's used to tackle many recurring challenges
 * in software development. such as:
 * 1- Representing hierarchical relationships
 * 2- Managing sorted data
 * 3- Facilitating fast lookup operations
 *
 * Just like #LinkedList the trees consists Node but the Node contains two child node
 *
 * Root: the topmost node in the tree is called the root of the tree. this node is the only node that has not parent
 * Leaf: A node that has no child is called as Leaf
 */
class TreeNode<T>(val value: T) {
    private val children: MutableList<TreeNode<T>> = mutableListOf()

    fun add(child: TreeNode<T>) = children.add(child)

    /**
     * Depth-first traversal starts at the root node and explores the tree as far as possible along each branch before
     * reaching a leaf and then backtracking.
     */
    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    /**
     * Level-order traversal is a technique that visits each node of the tree based on the depth of the nodes. Starting
     * at the root, every node on a level is visited before going to a lower level.
     */
    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)
        val queue = ArrayListQueue<TreeNode<T>>()
        children.forEach { queue.enqueue(it) }

        var node = queue.dequeue()
        while (node != null) {
            visit(node)
            node.children.forEach { queue.enqueue(it) }
            node = queue.dequeue()
        }
    }

    /**
     * Search by Level-order
     */
    fun searchByLevelOrder(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null
        forEachLevelOrder {
            if (it.value == value) {
                result = it
            }
        }
        return result
    }

    /**
     * Search by Depth-First
     */
    fun searchByDepthFirst(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null
        forEachDepthFirst {
            if (it.value == value) {
                result = it
            }
        }
        return result
    }

    /**
     * This function will count referenced nodes count inside each node and once iteration for each level is done
     * will print new line
     * Time complexity is O(n)
     */
    fun printLevels() {
        val queue = ArrayListQueue<TreeNode<T>>()
        var nodeLeftInCurrentLevel: Int
        queue.enqueue(this)
        while(queue.isEmpty.not()) {
            nodeLeftInCurrentLevel = queue.count
            while (nodeLeftInCurrentLevel > 0) {
                val node = queue.dequeue()
                node?.let {
                    print("${node.value} ")
                    node.children.forEach {
                        queue.enqueue(it)
                    }
                    nodeLeftInCurrentLevel--
                } ?: break
            }
            println()
        }
    }

}

typealias Visitor<T> = (TreeNode<T>) -> Unit