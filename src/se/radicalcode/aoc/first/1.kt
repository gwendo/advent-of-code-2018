package se.radicalcode.aoc.first

import java.io.File

fun main(args: Array<String>) {
    println(reduceNumbers(readFile(args[0])))
}

fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat)}

fun findDuplicateFrequencies(numbers: List<Int>) : Int {
    var frequencies = mutableListOf<Int>(0)
    var infiniteNumbers = numbers.asSequence().repeat().iterator()

    var dupFound = false;
    while (!dupFound) {
        var currChange = infiniteNumbers.next()
        var currFreq = frequencies.last() + currChange
        dupFound = frequencies.contains(currFreq)
        frequencies.add(currFreq);
    }
    return frequencies.last()
}



fun containsNumber(i: Int, frequencyList: MutableList<Int>) : Boolean {
    println("test: $i")
    println("lastFreq: ${frequencyList.last()}")
    return frequencyList.contains(i + frequencyList.last())
}

fun reduceNumbers(numbers: List<Int>) : Int  {
    return numbers.reduce {acc, b -> acc + b}
}

fun readFile(fileName: String) : List<Int> = File(fileName).useLines { it.map { it1 -> it1.removePrefix("+").toInt() }.toList() }

