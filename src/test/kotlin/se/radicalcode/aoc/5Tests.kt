package se.radicalcode.aoc

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.Test as test

class Test5 {
    @test fun testIsOppositePolarity() {
        assertTrue(isOppositePolarity('a', 'A'))
        assertFalse(isOppositePolarity('A', 'A'))
        assertFalse(isOppositePolarity('a', 'B'))
    }

    @test fun testDestroy() {
        val testString: String = "dabAcCaCBAcCcaDA"
        assertEquals("dabCBAcaDA", selfDestruct(testString))
    }

    @test fun testDestroyTestPolymer() {
        val testString: String = readFileAsStrings(ClassLoader.getSystemResource("5.in").file).first()
        val minPolymer = testString.toLowerCase().toCharArray().distinct().map{ it to selfDestruct(removeCharacter(it, testString)).length }.toMap().minBy { it.value }
        println(minPolymer!!.value)
        assertEquals(4992, minPolymer.value)
        assertEquals('o', minPolymer.key)
    }
}
