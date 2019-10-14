package se.radicalcode.aoc

data class Player(val id: Int, var score: Long)


class Game(private val numberOfPlayers:Int, private val numberOfMarbles: Int) {
    val gameBoard: MutableCircularList<Int> = ArrayList<Int>().circular()
    var players: List<Player>? = null
    var currentMarble = 0
    var currentMarbleIndex = 0
    var currentPlayer: Player? = null

    fun initGame() {
        players = IntRange(1, numberOfPlayers).map{Player(id=it, score= 0)}.circular()
        gameBoard.add(currentMarble)
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
            currentMarble = gameBoard.get(gameBoard.trueIndex(currentMarbleIndex - 6))
            currentMarbleIndex = gameBoard.trueIndex(currentMarbleIndex - 7)
            currentPlayer!!.score += gameBoard.removeAt(gameBoard.trueIndex(currentMarbleIndex))

        } else {
                currentMarbleIndex = gameBoard.trueIndex(currentMarbleIndex + 2)
                currentMarbleIndex = gameBoard.addAndReturnTrueIndex(currentMarbleIndex, turn)
                currentMarble = turn
        }
        //println(this.toString())
        if (turn % 100000 == 0 ) {
            //println(turn)
        }
    }

    fun findWinner() : Player? {
        return players!!.maxBy { it.score }
    }


    override fun toString() : String {
        val sb = ArrayList<String>()
        gameBoard.forEach{
            if (it == currentMarble ) {
                sb.add("($it)")
            } else {
                sb.add(it.toString())
            }
        }
        return sb.joinToString( separator = " ")
    }
}

class CircularList<out T>(private val list: List<T>) : List<T> by list {

    override fun get(index: Int): T =
        list[index.safely()]


    private fun Int.safely(): Int =
        if (this < 0) (this % size + size) % size
        else this % size

    override fun listIterator(index: Int): ListIterator<T> =
        list.listIterator(index.safely())

    override fun subList(fromIndex: Int, toIndex: Int): List<T> =
        list.subList(fromIndex.safely(), toIndex.safely())

    override fun toString(): String =
        list.toString()
}


fun <T> List<T>.circular(): CircularList<T> = CircularList(this)
fun <T> MutableList<T>.circular(): MutableCircularList<T> = MutableCircularList(this)

class MutableCircularList<T>(private val list: MutableList<T>): MutableList<T> by list {
    fun addAndReturnTrueIndex(index: Int, element: T) : Int {
        list.add(index.safely(), element)
        return index.safely()
    }

    fun trueIndex(index: Int) : Int =
        index.safely()


    override fun add(index: Int, element: T) =
        list.add(index.safely(), element)

    override fun addAll(index: Int, elements: Collection<T>): Boolean =
        list.addAll(index.safely(), elements)

    override fun set(index: Int, element: T): T =
        list.set(index.safely(), element)

    override fun get(index: Int): T =
        list[index.safely()]

    override fun listIterator(index: Int): MutableListIterator<T> =
        list.listIterator(index.safely())

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> =
        list.subList(fromIndex.safely(), toIndex.safely())

    override fun removeAt(index: Int): T =
        list.removeAt(index.safely())

    override fun toString(): String =
        list.toString()

    private fun Int.safely(): Int =
        if(this < 0) (this % size + size) % size
        else this % size

}


