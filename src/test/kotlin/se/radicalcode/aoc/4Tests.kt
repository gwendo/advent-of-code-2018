package se.radicalcode.aoc


import kotlin.test.assertEquals
import org.junit.Test as test
class TestGuardProblem {
    @test fun testParseEvent() {
        val input = "[1518-11-01 00:00] Guard #10 begins shift"
        val input2 = "[1518-10-16 23:59] Guard #409 begins shift"

        println(parseDate(input))
        println(parseDate(input2))
    }

    @test fun testParseGuardId() {
        val input = "[1518-11-01 00:00] Guard #10 begins shift"
        val input2 = "[1518-10-16 23:59] Guard #409 begins shift"
        val input3 = "[1518-11-05 00:45] falls asleep"
        val input4 = "[1518-11-05 00:55] wakes up"

        assertEquals(10, parseGuardId(input))
        assertEquals(409, parseGuardId(input2))
        assertEquals(-1, parseGuardId(input3))
        assertEquals(-1, parseGuardId(input4))
    }

    @test fun testOrderList() {


        var eventList = readFileAsStrings(ClassLoader.getSystemResource("4.in").file)
            .map{ parseEvent(it)}
            .sortedBy { it.dateTime }
            .toMutableList()

        var previousEvent: GuardEvent? = null
        for (guardEvent in eventList) {
            if (guardEvent.guardId == -1 && previousEvent != null) {
                guardEvent.guardId = previousEvent.guardId
            }
            previousEvent = guardEvent
        }

        val guardEventList = eventList
            .filter{ge -> ge.event == EventType.SLEEP || ge.event == EventType.WAKEUP}
            .groupBy{e -> e.guardId}
            .mapValues { entry -> entry.value.groupBy { ge -> ge.monthDay } }
            .mapValues { entry2 -> entry2.value.entries.map{ el ->  initSleepArray(el.value)} }

        val sleepyHead = guardEventList.mapValues { entry -> entry.value.map{it.sum()}.sum() }
            .maxBy { entry -> entry.value }

        val maxSleepMinuteMap = guardEventList
            .mapValues{ entry -> entry.value.map { it.withIndex().toList() }.flatten().groupBy { it.index }.mapValues{ entry -> entry.value.count{ it.value == 1}}.maxBy { it.value }}

        maxSleepMinuteMap.forEach { (t, u) -> println("$t->$u")  }
        val maxEntry = maxSleepMinuteMap.maxBy { entry -> entry.value!!.value }
        if (maxEntry?.value != null) {
            println(maxEntry.key * maxEntry.value!!.key)
        }


        if (sleepyHead != null) {
            val maxEntry = guardEventList.getValue(sleepyHead.key)
               .map { it.withIndex().toList() }.flatten().groupBy { it.index }.mapValues{ entry -> entry.value.count{ it.value == 1}}.maxBy { it.value }
           if (maxEntry != null) {
               println(maxEntry.key * sleepyHead.key)
            }
        }










    }
}
