package se.radicalcode.aoc

fun calculateFuelCell(fuelCell: Pair<Int, Int>, serialNo: Int): Int {
    val rackId = fuelCell.first + 10
    var powerLevel = (rackId * fuelCell.second)
    powerLevel += serialNo
    powerLevel *= rackId
    val hunderedDigit = if (powerLevel < 100)  0 else powerLevel.toString().toCharArray().get(powerLevel.toString().length -3).toString().toInt()
    return hunderedDigit - 5
}

fun calculate3x3Grid(topLeft: Pair<Int, Int>, serialNo: Int): Int {
    val grid = ArrayList<Pair<Int,Int>>()
    for(y in 0..2) {
        for (x in 0..2) {
           grid.add(Pair(topLeft.first + x, topLeft.second + y))
        }
    }
    val sum = grid.map{ calculateFuelCell(it, serialNo)}.sum()
    return sum
}