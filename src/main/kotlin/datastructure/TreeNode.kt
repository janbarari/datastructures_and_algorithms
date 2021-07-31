package datastructure

import list.ArrayListQueue
import java.util.*

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
     * Search with Level-order
     */
    fun searchWithLevelOrder(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null
        forEachLevelOrder {
            if (it.value == value) {
                result = it
            }
        }
        return result
    }

    /**
     * Search with Depth-First
     */
    fun searchWithDepthFirst(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null
        forEachDepthFirst {
            if (it.value == value) {
                result = it
            }
        }
        return result
    }

}

typealias Visitor<T> = (TreeNode<T>) -> Unit

fun makeBeverageTree(): TreeNode<String> {
    val tree = TreeNode("Beverages")

    val hot = TreeNode("hot")
    val cold = TreeNode("cold")

    val tea = TreeNode("tea")
    val coffee = TreeNode("coffee")
    val chocolate = TreeNode("cocoa")

    val blackTea = TreeNode("black")
    val greenTea = TreeNode("green")
    val chaiTea = TreeNode("chai")

    val soda = TreeNode("soda")
    val milk = TreeNode("milk")

    val gingerAle = TreeNode("ginger ale")
    val bitterLemon = TreeNode("bitter lemon")

    tree.add(hot)
    tree.add(cold)

    hot.add(tea)
    hot.add(coffee)
    hot.add(chocolate)

    cold.add(soda)
    cold.add(milk)

    tea.add(blackTea)
    tea.add(greenTea)
    tea.add(chaiTea)

    soda.add(gingerAle)
    soda.add(bitterLemon)

    return tree
}

fun main() {
    val tree = makeBeverageTree()

    println("Please enter the value:")
    val query: String = readLine()!!

    var lfTimestamp: Long =  System.nanoTime()
    tree.searchWithLevelOrder(query)?.let {
        lfTimestamp = System.nanoTime() - lfTimestamp
        println("Found with Level-first: ${it.value}, nt: $lfTimestamp")
    } ?: println("Couldn't find: $query")

    var dfTimestamp: Long = System.nanoTime()
    tree.searchWithDepthFirst(query)?.let {
        dfTimestamp = System.nanoTime() - dfTimestamp
        println("Found with Depth-first: ${it.value}, nt: $dfTimestamp")
    } ?: println("Couldn't find: $query")

    if (dfTimestamp < lfTimestamp) {
        println("Winner algorithm: Depth-first")
    } else {
        println("Winner algorithm: Level-first")
    }
}

private fun calculateTheDiff(min: Long, max: Long): Float {
    return max / (max/min).toFloat()
}