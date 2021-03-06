package se.radicalcode.aoc

import java.io.File

fun main(args: Array<String>) {
    println(reduceNumbers(readFileAsInts(args[0])))
}

fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat)}

fun findDuplicateFrequencies(numbers: List<Int>) : Int {
    val frequencies = mutableListOf<Int>(0)
    val infiniteNumbers = numbers.asSequence().repeat().iterator()

    var dupFound = false;
    while (!dupFound) {
        val currChange = infiniteNumbers.next()
        val currFreq = frequencies.last() + currChange
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

fun readFileAsInts(fileName: String) : List<Int> = File(fileName).useLines { it.map { it1 -> it1.removePrefix("+").toInt() }.toList() }
fun readFileAsStrings(fileName: String) : List<String> = File(fileName).useLines { it.toList() }

