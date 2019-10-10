package se.radicalcode.aoc
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.Test as test

class Test7 {
    @test fun test7(){
        val stepPairs = readFileAsStrings(ClassLoader.getSystemResource("7test-input.in").file)
            .map{extractPair(it)}

        val preReqs =
                    stepPairs
                        .groupBy { it.second }
                        .mapValues{ it.value }
                        .mapValues{ entry -> entry.value.map{it.first}}
                        .forEach{println(it)}

        val nextSteps = stepPairs
            .groupBy { it.first }
            .mapValues{ it.value }
            .mapValues{ entry -> entry.value.map{it.second}}
            .forEach{println(it)}




    }


}