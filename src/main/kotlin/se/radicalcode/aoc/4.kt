package se.radicalcode.aoc

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class GuardEvent(val dateTime: LocalDateTime, val monthDay: String, val minute: Int,  val event: EventType, var guardId: Int)

enum class EventType {
    SHIFT_START, SLEEP, WAKEUP
}
fun getMonthDay(date: LocalDateTime): String {
    return date.monthValue.toString() + '-' + date.dayOfMonth.toString()
}

fun parseDate(inputStr: String): LocalDateTime {
    var dateStr = inputStr.split(']')[0].trimStart('[').replace(' ', 'T')
    return LocalDateTime.parse(dateStr)
}

fun parseGuardId(inputStr: String): Int {
    var guardId = -1
    if (inputStr.contains("#")) {
        guardId = Integer.parseInt(inputStr.substringAfter('#').substringBefore(' '))
    }
    return guardId
}

fun parseEvent(inputStr: String): GuardEvent {
    return GuardEvent(dateTime = parseDate(inputStr), monthDay = getMonthDay(parseDate(inputStr)), minute= parseDate(inputStr).minute,  event = EventTypeFactory.eventType(inputStr.split(']')[1].trimStart()), guardId = parseGuardId(inputStr) )
}
fun initArray(start: Int, end: Int, sleepArray: IntArray): IntArray{
    IntRange(start,end-1).forEach{sleepArray[it]=1}
    return sleepArray;
}

fun sumArray(arr: IntArray): Int {
    return arr.sum()
}

fun initSleepArray(guardEventList: List<GuardEvent>): IntArray {
    val sleepArray = IntArray(60) { _ -> 0 }
    guardEventList.chunked(2).forEach{pair -> initArray(pair.get(0).minute, pair.get(1).minute, sleepArray)}
    return sleepArray
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