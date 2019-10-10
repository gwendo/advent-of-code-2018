package se.radicalcode.aoc

import java.lang.Math.abs

fun manhattanDistance(x0: Int, y0: Int, x1: Int, y1: Int) : Int {
    return abs(x0-x1) + abs(y0-y1)
}