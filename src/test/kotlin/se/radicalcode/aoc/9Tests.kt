package se.radicalcode.aoc

import kotlin.test.assertEquals
import org.junit.Test as test
import org.junit.Ignore as ignore

class Test9 {
    @test
    fun testGameInit() {
        val g = Game(9, 25)
        g.initGame()
        g.runGame()
        assertEquals(32, g.findWinner()!!.score)
    }

    @test fun testGameInitLinked() {
        val g = LinkedGame(9, 25)
        g.initGame()
        g.runGame()
        assertEquals(32, g.findWinner()!!.score)
    }

    @test
    fun testGame2() {
        val g2 = Game(10, 1618)
        g2.initGame()
        g2.runGame()
        assertEquals(8317, g2.findWinner()!!.score)
    }

    @test
    fun testGame2Linked() {
        val g2 = LinkedGame(10, 1618)
        g2.initGame()
        g2.runGame()
        assertEquals(8317, g2.findWinner()!!.score)
    }

    @test
    fun testGame3() {
        val g3 = Game(13, 7999)
        g3.initGame()
        g3.runGame()
        assertEquals(146373, g3.findWinner()!!.score)
    }

    @test
    fun testGame3Linked() {
        val g3 = LinkedGame(13, 7999)
        g3.initGame()
        g3.runGame()
        assertEquals(146373, g3.findWinner()!!.score)
    }

    @test
    fun testGame4() {
        val g4 = Game(17, 1104)
        g4.initGame()
        g4.runGame()
        assertEquals(2764, g4.findWinner()!!.score)
    }

    @test
    fun testGame5() {
        val g5 = Game(21, 6111)
        g5.initGame()
        g5.runGame()
        assertEquals(54718, g5.findWinner()!!.score)
    }

    @test
    fun testGame5Linked() {
        val g5 = LinkedGame(21, 6111)
        g5.initGame()
        g5.runGame()
        assertEquals(54718, g5.findWinner()!!.score)
    }

    @test
    fun testGame6() {
        val g6 = Game(30, 5807)
        g6.initGame()
        g6.runGame()
        assertEquals(37305, g6.findWinner()!!.score)
    }
    @test
    fun testGame6Linked() {
        val g6 = LinkedGame(30, 5807)
        g6.initGame()
        g6.runGame()
        assertEquals(37305, g6.findWinner()!!.score)
    }

    @test
    fun testGameReal() {
        val g = Game(419, 71052)
        g.initGame()
        g.runGame()
        println(g.findWinner()!!.score)
    }

    @test
    fun testGameRealLinked() {
        val g = LinkedGame(419, 71052)
        g.initGame()
        g.runGame()
        println(g.findWinner()!!.score)
    }

    @ignore
    @test
    fun testGameReal100TimesLarger() {
        val g = Game(419, 7105200)
        g.initGame()
        g.runGame()
        println(g.findWinner()!!.score)
    }

    @test fun testGameReal100TimesLargerLinked() {
        val g = LinkedGame(419, 7105200)
        g.initGame()
        g.runGame()
        println(g.findWinner()!!.score)
    }

}
