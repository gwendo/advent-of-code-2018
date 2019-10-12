package se.radicalcode.aoc
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import org.junit.Test as test

class Test7 {
    @test fun test7A(){
        println(
            takeNext(
                resultString = "",
                stepDefs = generateStepCollection(
                    readFileAsStrings(ClassLoader.getSystemResource("7.in").file)
                        .map{extractPair(it)}, emptyMap()
                )
            )
        )

    }

    @test fun test7B(){
        var pairList =readFileAsStrings(ClassLoader.getSystemResource("7test-input.in").file)
            .map{extractPair(it)}

        var stepList = pairList.unzip().toList().flatten().distinct()
        val executionTimeMap = stepList.sorted().zip(IntRange(1, stepList.size)).toMap()


        var stepCollection = generateStepCollection( pairList, executionTimeMap)

        println(startWork(2, stepCollection))
    }

    @test fun test7BFull(){
        var pairList =readFileAsStrings(ClassLoader.getSystemResource("7.in").file)
            .map{extractPair(it)}

        var stepList = pairList.unzip().toList().flatten().distinct()
        val executionTimeMap = stepList.sorted().zip(IntRange(1, stepList.size)).toMap()


        var stepCollection = generateStepCollection( pairList, executionTimeMap, 60)

        println(startWork(5, stepCollection))
    }

    @test fun isWorkComplete() {
        val w = Worker(id= 1, workStep = StepDef(step = "A", executionTime = 1, preReqs = emptyList()))
        assertTrue(w.isBusy())
        assertFalse(w.isWorkComplete())
        w.tick()
        assertTrue(w.isWorkComplete())
        w.collectWork()
        assertNull(w.workStep)
        assertTrue(w.isFree())
    }

}