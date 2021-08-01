package datastructure.stack

interface Stack<T> {
    fun push(element: T)
    fun pop(): T?
}