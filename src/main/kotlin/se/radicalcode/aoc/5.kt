package se.radicalcode.aoc



fun isOppositePolarity(a: Char, b: Char): Boolean {
    return a != b && a.toLowerCase() == b.toLowerCase()
}

fun selfDestruct(polymer: String): String {
    var polymerString = polymer
    var currIndex = 0
    while (currIndex < polymerString.length-1){
        if (isOppositePolarity(polymerString[currIndex], polymerString[currIndex+1])){
            polymerString = polymerString.removeRange(currIndex, currIndex+2)
            if (currIndex > 0) {
                currIndex--
            }
        } else {
            currIndex++
        }
    }
    return polymerString
}

fun removeCharacter(a: Char, myString: String) : String {
    return myString.replace(a.toLowerCase().toString(), "").replace(a.toUpperCase().toString(), "")
}
