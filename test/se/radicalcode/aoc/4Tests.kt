package se.radicalcode.aoc


import org.junit.Test as test
class TestGuardProblem {
    @test fun testParseEvent() {
        val input = "[1518-11-01 00:00] Guard #10 begins shift"
        val input2 = "[1518-10-16 23:59] Guard #409 begins shift"

        println(parseDate(input))
        println(parseDate(input2))
    }

    @test fun testOrderList() {
        var eventList = readFileAsStrings(".\\out\\test\\advent-of-code\\4.in")
            .map{ GuardEvent(time= parseDate(it), event = it.split(']')[1].trimStart())}.sortedBy { it.time }

        for (event in eventList) {
            switch

        }
    }
}
