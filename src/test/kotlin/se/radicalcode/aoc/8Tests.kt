package se.radicalcode.aoc

import org.junit.Test as test

class Test8 {
    @test fun testParseNode() {
        val testArray = readFileAsStrings(ClassLoader.getSystemResource("8test-input.in").file).first().split(" ").map { it.toInt()}
        val root = parseNodes(testArray)
        println(root)
        println(sumMetaData(0, root))
        println(getNodeValue(root))
    }

    @test fun testSumFullNode() {
        val testArray = readFileAsStrings(ClassLoader.getSystemResource("8.in").file).first().split(" ").map { it.toInt()}
        val root = parseNodes(testArray)
        println(root)
        println(sumMetaData(0, root))
        println(getNodeValue(root))

    }


}