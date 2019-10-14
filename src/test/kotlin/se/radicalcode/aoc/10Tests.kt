package se.radicalcode.aoc

import kotlin.test.assertEquals
import org.junit.Test as test

class Tests10 {
    @test fun testParseString() {
        val positionString = "position=< 9,  1> velocity=< 0,  2>"
        val point = LightPoint.parseString(positionString);
        assertEquals(point.point.first, 9)
        assertEquals(point.point.second, 1)
        assertEquals(point.velocity.first, 0)
        assertEquals(point.velocity.second, 2)
    }

    @test fun testMove() {
        val point = LightPoint(Pair(9, 1), Pair(1, 2))
        point.move()
        assertEquals(point.point.first, 10)
        assertEquals(point.point.second, 3)

    }

    @test fun testPrint() {
        val s = Sky()
        s.addLightPoint(LightPoint(Pair(0,0), Pair(1, 2)))
        s.addLightPoint(LightPoint(Pair(-10, -10), Pair(1, 2)))
        s.addLightPoint(LightPoint(Pair(10, 10), Pair(1, 2)))
        s.print()

    }

    @test fun testExampleProblem() {
        val myPoints = readFileAsStrings(ClassLoader.getSystemResource("10test-input.in").file).map { LightPoint.parseString(it) }
        val s = Sky()
        myPoints.forEach { s.addLightPoint(it) }
        s.setSkyDimension()
        while( !s.solved()){
            s.tick()
        }
        s.print()
    }

    @test fun testTestProblem() {
        val myPoints = readFileAsStrings(ClassLoader.getSystemResource("10.in").file).map { LightPoint.parseString(it) }
        val s = Sky()
        myPoints.forEach { s.addLightPoint(it) }
        s.setSkyDimension()
        while( !s.solved()){
            s.tick()
        }
        s.print()
    }
}

