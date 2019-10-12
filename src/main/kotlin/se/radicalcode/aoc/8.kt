package se.radicalcode.aoc

data class Node(val children: MutableList<Node>, val metadata: MutableList<Int>)

fun parseNodes(intArray: List<Int>): Node {
    val nodeList = ArrayList<Node>()
    parseNode(intArray.subList(2, intArray.size), intArray[0], intArray[1], nodeList)
    return nodeList.first()
}

fun parseNode(intArray: List<Int>, numChild: Int, numMetadata: Int, collector: MutableList<Node>): List<Int> {
    val node = Node(ArrayList(), ArrayList())
    var remainingArray = intArray
    while(node.children.size < numChild) {
        remainingArray = parseNode(remainingArray.subList(2, remainingArray.size), remainingArray[0], remainingArray[1], node.children)
    }
    if (numMetadata > 0) {
        node.metadata.addAll(remainingArray.slice(IntRange(0, numMetadata-1)))
        remainingArray = remainingArray.subList(numMetadata, remainingArray.size)
    }
    collector.add(node)
    return remainingArray
}

fun getNodeValue( node: Node?): Int {
    var value = 0;
    if (node == null) return value;
    value = if (node.children.isEmpty()) {
        node.metadata.sum()
    } else {
        node.metadata.map{ getNodeValue(node.children.getOrNull(it-1))}.sum()
    }
    return value
}

fun sumMetaData(acc: Int, node: Node): Int {
    val sum = node.children.fold(acc, { acc1, node1 -> sumMetaData(acc1, node1)})
    return sum + node.metadata.sum();
}
