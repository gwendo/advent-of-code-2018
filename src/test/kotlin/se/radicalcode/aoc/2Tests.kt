package se.radicalcode.aoc

import kotlin.test.assertEquals
import org.junit.Test as test

class TestChecksum {


    @test fun testCalculateChecksum() {
        var list = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
        assertEquals(12, calculateChecksum(list))
    }

    @test fun solveTwoPartOne() {

        println(calculateChecksum(readFileAsStrings(ClassLoader.getSystemResource("2.in").file)))
    }

    @test fun testDiffCount() {
        assertEquals(5, diffCount("abcde", "fghij"))
        assertEquals(2, diffCount("abcde", "axcye"))
        assertEquals(1, diffCount("fghij", "fguij"))
    }

    @test fun testRemoveDiffingLetter() {
        assertEquals("fgij", removeDifferentChar("fghij", "fguij"))
    }

    @test fun testCombineLists() {
        var list = listOf("abcde","fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")
        list.combine(list).filter{ diffCount(
            it.first,
            it.second
        ) == 1}.forEach {println(removeDifferentChar(it.first, it.second))}
    }

    @test fun solveTwoPartTwo() {
        var list = readFileAsStrings(ClassLoader.getSystemResource("2.in").file)
        list.combine(list)
            .filter{ diffCount(it.first, it.second) == 1}
            .forEach{ println(removeDifferentChar(it.first, it.second))}
    }
}
