package se.radicalcode.aoc

fun extractPair(it: String) : Pair<String,String> {
    return it
        .replace("Step ", "")
        .replace("must be finished before step", ",")
        .replace("can begin.", "")
        .split(",").chunked(2).map{Pair(it.first().trim(), it.last().trim())}.first()
}

