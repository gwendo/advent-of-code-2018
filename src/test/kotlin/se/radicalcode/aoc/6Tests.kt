package se.radicalcode.aoc
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.Test as test

class Test6 {
    @test fun testManhattanDistance(){
        assertEquals(2, manhattanDistance(0, 0, 1,1))
        assertEquals(1, manhattanDistance(0, 1, 1,1))
        assertEquals(5, manhattanDistance(2, 3, 0,0))

    }
}