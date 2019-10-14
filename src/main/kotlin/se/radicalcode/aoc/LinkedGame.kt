package se.radicalcode.aoc

class LinkedGame(private val numberOfPlayers:Int, private val numberOfMarbles: Int) {
    val gameBoard: MyCircularLinkedList<Int>? =  MyCircularLinkedList(MyListNode(0))
    var players: List<Player>? = null
    var currentPlayer: Player? = null

    fun initGame() {
        players = IntRange(1, numberOfPlayers).map{Player(id=it, score= 0)}.circular()
    }

    fun runGame() {
        var turn = 1
        while(turn <= numberOfMarbles ) {
            processTurn(turn++)
        }
    }

    fun processTurn(turn: Int) {
        currentPlayer = players!!.get(turn)
        if (turn % 23 == 0) {
            currentPlayer!!.score += turn
            currentPlayer!!.score += gameBoard!!.removeAt(-7).value

        } else {

                gameBoard!!.add(1, MyListNode(turn))

        }
        //println(this.toString())
        if (turn % 100000 == 0 ) {
            //println(turn)
        }
    }

    fun findWinner() : Player? {
        return players!!.maxBy { it.score }
    }

    override fun toString(): String {
        var node = gameBoard!!.currentNode
        while (node!!.value != 0) {
            node = node.next
        }
        var valueList:  ArrayList<Int> = ArrayList()
        do {
            valueList.add(node!!.value)
            node = node.next
        } while (node!!.value != 0)
        return valueList.joinToString() {i ->  if (i == gameBoard!!.currentNode!!.value) "($i)" else i.toString()}
    }

}
