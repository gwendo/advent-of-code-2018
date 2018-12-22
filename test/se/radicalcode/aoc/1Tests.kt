package se.radicalcode.aoc

import kotlin.test.assertEquals
import org.junit.Test as test

class TestFileRead {
    @test fun canReadFile() {
        val rows = readFileAsInts(".\\out\\test\\advent-of-code\\testfile.in")
        assertEquals(rows.count(), 5)
    }

    @test fun canReduceNumbers() {
        val l1 = listOf(1, -2, 3, 1)
        var reduceVal = reduceNumbers(l1)
        assertEquals(3, actual = reduceVal)

        val l2 = listOf(1,1,1)
        reduceVal = reduceNumbers(l2)
        assertEquals(3, actual= reduceVal)

        val l3 = listOf(1, 1, -2)
        reduceVal = reduceNumbers(l3)
        assertEquals(0, actual = reduceVal)

        val l4 = listOf(-1, -2, -3)
        reduceVal = reduceNumbers(l4)
        assertEquals(-6, actual = reduceVal)
    }

    @test fun canFindDupFrequencies() {
        val l1 = listOf(1, -1)
        var foundDup = findDuplicateFrequencies(l1)
        assertEquals(0, foundDup)

        var l2 = listOf(+3, +3, +4, -2, -4)
        foundDup = findDuplicateFrequencies(l2)
        assertEquals(10, foundDup)

        var l3 = listOf(-6, +3, +8, +5, -6)
        foundDup = findDuplicateFrequencies(l3)
        assertEquals(5, foundDup)

        var l4 = listOf(+7, +7, -2, -7, -4)
        foundDup = findDuplicateFrequencies(l4)
        assertEquals(14, foundDup)
    }

    @test fun solveTest() {
        assertEquals(3,
            reduceNumbers(readFileAsInts(".\\out\\test\\advent-of-code\\testfile.in"))
        )
    }

    @test fun solveOne() {
        println(reduceNumbers(readFileAsInts(".\\out\\test\\advent-of-code\\1.in")))
    }

    @test fun solveStepTwo() {
        println(findDuplicateFrequencies(readFileAsInts(".\\out\\test\\advent-of-code\\1.in")))
    }
}