package se.radicalcode.aoc

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.Test as test
import kotlin.test.assertTrue

class TestFabric {
    @test
    fun testParseInput() {
        val fabricList = readFileAsStrings(".\\out\\test\\advent-of-code\\3test-input.in")
            .map{ FabricFactory.newFabricClaim(it)}

        fabricList.forEach {println(it)}
  }
    @test fun testIsLocationInside() {

        assertTrue(isLocationInside(Pair(3,3), FabricFactory.newFabricClaim("#2 @ 3,1: 4x4")))
        assertFalse(isLocationInside(Pair(0,0), FabricFactory.newFabricClaim("#2 @ 3,1: 4x4")))
    }

    @test fun testFindOverlaps(){
        val fabricList = readFileAsStrings(".\\out\\test\\advent-of-code\\3test-input.in")
            .map{ FabricFactory.newFabricClaim(it)}
        var noOfOverlapping = (0..10).toList().combine((0..8)).map{ p -> fabricList.filter { isLocationInside(p, it)}.count()}.filter{ it > 1}.count()

        assertEquals(4, noOfOverlapping)
        val fabricList2 = readFileAsStrings(".\\out\\test\\advent-of-code\\3.in")
            .map{ FabricFactory.newFabricClaim(it)}
        var noOfOverlapping2 = (0..1000).toList().combine((0..1000)).map{ p -> fabricList2.filter { isLocationInside(p, it)}.count()}.filter{ it > 1}.count()
        assertEquals(118322, noOfOverlapping2)
    }

    @test fun testFindNonOverlapping() {
        val fabricList = readFileAsStrings(".\\out\\test\\advent-of-code\\3test-input.in")
            .map{ FabricFactory.newFabricClaim(it)}

        (0..10).toList().combine((0..8))
            .map{ point -> fabricList.filter { isLocationInside(point , it)}}
            .filter { it.count() == 1}
            .groupingBy{ it}
            .eachCount()
            .filter{ it.value == it.key.first().height* it.key.first().width}.forEach{ println(it)}

    }

    @test fun solveThreePartTwo() {
        val fabricList2 = readFileAsStrings(".\\out\\test\\advent-of-code\\3.in")
            .map{ FabricFactory.newFabricClaim(it)}
        (0..1000).toList().combine((0..1000))
            .map{ p -> fabricList2.filter { isLocationInside(p, it)}}
            .filter{ it.count() == 1}
            .groupingBy{ it}
            .eachCount()
            .filter{ it.value == it.key.first().height* it.key.first().width}.forEach{ println(it)}
    }

}