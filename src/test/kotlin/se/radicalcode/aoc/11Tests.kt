package se.radicalcode.aoc

import kotlin.test.assertEquals
import org.junit.Test as test

class Tests11 {
    @test fun testPowerCellLevel() {
        assertEquals(4, calculateFuelCell(Pair(3,5), 8))
        assertEquals(-5, calculateFuelCell(Pair(122, 79), 57))
        assertEquals(0, calculateFuelCell(Pair(217,196), 39))
        assertEquals(4, calculateFuelCell(Pair(101,153), 71))
    }

    @test fun testGridValue() {
        val grid = ArrayList<Pair<Pair<Int,Int>,Int>>()
        for (y in 1..297) {
            for (x in 1..297) {
                grid.add(Pair(Pair(x, y), calculate3x3Grid(Pair(x,y),18 )))
            }
        }
        val max = grid.map{ it.first to it.second}.maxBy {it.second }
        println(max)


    }

    @test fun testGridValue2() {
        val grid = ArrayList<Pair<Pair<Int,Int>,Int>>()
        for (y in 1..297) {
            for (x in 1..297) {
                grid.add(Pair(Pair(x, y), calculate3x3Grid(Pair(x,y),42 )))
            }
        }
        val max = grid.map{ it.first to it.second}.maxBy {it.second }
        println(max)


    }

    @test fun testGridValue3() {
        val grid = ArrayList<Pair<Pair<Int,Int>,Int>>()
        for (y in 1..297) {
            for (x in 1..297) {
                grid.add(Pair(Pair(x, y), calculate3x3Grid(Pair(x,y),9995 )))
            }
        }
        val max = grid.map{ it.first to it.second}.maxBy {it.second }
        println(max)


    }
}