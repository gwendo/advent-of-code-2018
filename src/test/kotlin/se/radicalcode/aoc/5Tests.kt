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
        var testString: String = "dabAcCaCBAcCcaDA"

        assertEquals("dabCBAcaDA", selfDestruct(testString))
    }

    @test fun testDestroyTestPolymer() {
        var testString: String = readFileAsStrings(ClassLoader.getSystemResource("5.in").file).first()

        assertEquals(9900,  selfDestruct(testString).length)
    }
}
