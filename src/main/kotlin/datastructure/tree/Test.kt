package datastructure.tree

import datastructure.TreeNode
import list.ArrayListQueue

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

fun challengeOne() {
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

    root.printEachLevel()
}

fun main() {
    challengeOne()
}