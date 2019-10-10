package se.radicalcode.aoc

data class FabricClaim(val id: String, val location: Pair<Int, Int>, val width: Int, val height: Int)

data class User(val name: String, val age: Int)

fun isLocationInside(location: Pair<Int, Int>, claim: FabricClaim) : Boolean {
    return  (location.first >= claim.location.first && location.first <= claim.location.first + claim.width-1) &&
            (location.second >= claim.location.second && location.second <= claim.location.second + claim.height-1)
}

interface FabricFactory {

    companion object {
        fun newFabricClaim(inputText: String) : FabricClaim {
            val foo = inputText.split(' ')
            val id = foo[0]
            val pos = foo[2].trimEnd(':').split(',')
            val size = foo[3].split('x')
            return FabricClaim(id, Pair(pos[0].toInt(), pos[1].toInt()), width = size[0].toInt(), height = size[1].toInt())
        }
    }
}

