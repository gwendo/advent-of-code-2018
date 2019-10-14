package se.radicalcode.aoc

data class MyListNode<T> (val value: T, var prev: MyListNode<T>? = null, var next: MyListNode<T>? = null)

class MyCircularLinkedList<T> {
    constructor(node: MyListNode<T>){
        currentNode = node;
        currentNode!!.prev = node;
        currentNode!!.next = node;
    }

    var currentNode: MyListNode<T>? = null

    fun add(relativeIndex: Int, node: MyListNode<T>){
        setCurrentToRelativeIndex(relativeIndex)
        val nextNode = currentNode!!.next
        currentNode!!.next = node
        node.prev = currentNode
        node.next = nextNode
        nextNode!!.prev = node
        currentNode = currentNode!!.next
    }

    fun removeAt(relativeIndex: Int): MyListNode<T> {
        setCurrentToRelativeIndex(relativeIndex)
        val oldCurrent = currentNode
        var prevNode = currentNode!!.prev
        var nextNode = currentNode!!.next
        prevNode!!.next = nextNode
        nextNode!!.prev = prevNode
        currentNode = nextNode
        return oldCurrent!!
    }

    private fun setCurrentToRelativeIndex(relativeIndex: Int) {
        var stepCount = 0
        if (relativeIndex < 0) {
            while (stepCount > relativeIndex) {
                currentNode = currentNode!!.prev
                stepCount--
            }
        } else {
            while (stepCount < relativeIndex) {
                currentNode = currentNode!!.next
                stepCount++
            }
        }
    }
}
