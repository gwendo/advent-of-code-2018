package se.radicalcode.aoc

import java.util.Comparator
import kotlin.math.abs

class LightPoint(var point: Pair<Int, Int>, val velocity: Pair<Int, Int>) {

    fun move(): LightPoint {
        return LightPoint(Pair(point.first + velocity.first, point.second + velocity.second), this.velocity)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LightPoint) return false

        if (point != other.point) return false

        return true
    }

    override fun hashCode(): Int {
        return point.hashCode()
    }

    companion object {
        fun parseString(objectString: String): LightPoint {
            val pointArray = objectString
                .substringAfter("position=<")
                .substringBefore(">")
                .split(",").map {it.trim().toInt()}
            val velocityArray = objectString
                .substringAfterLast("<")
                .substringBefore(">")
                .split(",").map{it.trim().toInt()}
            return LightPoint(Pair(pointArray.first(), pointArray.last()), Pair(velocityArray.first(), velocityArray.last()))
        }
    }
}

class Sky {
    var dimension: Pair<Pair<Int, Int>, Pair<Int,Int>> = Pair(Pair(0,0), Pair(0,0))
    var solved: Boolean = false
    var tickCount = 0
    var pointList: MutableList<LightPoint> = ArrayList()
    fun addLightPoint(point: LightPoint) {
        pointList.add(point)
        dimension = findSkyDimension(pointList)
        orderPoints()
    }

    fun setSkyDimension() {
        dimension = findSkyDimension(pointList)
    }


    fun findSkyDimension(pList: List<LightPoint>) : Pair<Pair<Int, Int>, Pair<Int, Int>> {
        return Pair(Pair(pList.minBy{it.point.first}!!.point.first, pList.minBy{it.point.second}!!.point.second), Pair(pList.maxBy{it.point.first}!!.point.first, pList.maxBy{it.point.second}!!.point.second))
    }

    private fun orderPoints() {
        pointList.sortedWith(Comparator<LightPoint>{ p0:LightPoint, p1:LightPoint -> if (p0.point.second.compareTo(p1.point.second) ==0) p0.point.first.compareTo(p1.point.first) else p0.point.second.compareTo(p1.point.second)   })
    }

    fun tick() {
        val nextList = pointList.map {it.move()}.toMutableList()
        val nextDimension = findSkyDimension(nextList)
        solved = abs(dimension.first.second - dimension.second.second) < abs(nextDimension.first.second - nextDimension.second.second)
        if (!solved) {
            tickCount++
            pointList = nextList
            dimension = nextDimension
        }
        orderPoints()
    }

    fun solved() : Boolean {
        return solved
    }

     fun  print() {
         println("----------- $tickCount -----------")
        for (y in IntRange(dimension.first.second, dimension.second.second)){
            for(x in IntRange(dimension.first.first, dimension.second.first)) {
                if (pointList.contains(LightPoint(Pair(x, y), Pair(0,0)))) {
                    print("X")
                } else {
                    print(".")
                }
            }
            println()
        }
    }


}