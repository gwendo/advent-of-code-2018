package se.radicalcode.aoc

import java.text.SimpleDateFormat
import java.util.*

data class GuardEvent(val time: Date, val event: String )

enum class EventType {
    SHIFT_START, SLEEP, WAKEUP
}

fun parseDate(inputStr: String): Date {
    var dateStr = inputStr.split(']')[0].trimStart('[')
    return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateStr)
}

interface EventTypeFactory {

    companion object {
        fun eventType(event: String) =
        when (event.split(' ')[0]) {
            "Guard" -> EventType.SHIFT_START
            "falls" -> EventType.SLEEP
            "wakes" -> EventType.WAKEUP
            else -> throw Exception("I don't know how to deal with $event.")
        }
    }
}