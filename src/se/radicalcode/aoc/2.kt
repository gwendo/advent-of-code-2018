package se.radicalcode.aoc

fun hasNumberOfUniqueLetter(word: String, count: Int): Boolean {
    return word.asSequence().groupingBy { it }.eachCount().filter {it.value == count}.count() > 0
}

fun calculateChecksum(wordList: List<String>): Int {
    var hasTwoLetters = wordList.filter{ hasNumberOfUniqueLetter(it, 2) }.count()
    var hasThreeLetters = wordList.filter{ hasNumberOfUniqueLetter(it, 3) }.count()
    return (hasThreeLetters *  hasTwoLetters)
}

fun diffCount(word1: String, word2: String) : Int {
    return word1.zip(word2).filter{ it.first != it.second }.count()
}

fun removeDifferentChar(word1: String, word2: String): String {
    return word1.zip(word2).filter { it.first == it.second }.map { it.first }.joinToString(separator = "")
}

fun <T1, T2> Collection<T1>.combine(other: Iterable<T2>): List<Pair<T1, T2>> {
    return combine(other, {thisItem: T1, otherItem: T2 -> Pair(thisItem, otherItem) })
}

fun <T1, T2, R> Collection<T1>.combine(other: Iterable<T2>, transformer: (thisItem: T1, otherItem:T2) -> R): List<R> {
    return this.flatMap { thisItem -> other.map { otherItem -> transformer(thisItem, otherItem) }}
}
