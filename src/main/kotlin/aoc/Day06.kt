package aoc

import aoc.Utils.readInput

fun simulate(shoal: Map<Int, Long>): Map<Int, Long> {
    return mapOf(
        0 to shoal.getOrDefault(1, 0),
        1 to shoal.getOrDefault(2, 0),
        2 to shoal.getOrDefault(3, 0),
        3 to shoal.getOrDefault(4, 0),
        4 to shoal.getOrDefault(5, 0),
        5 to shoal.getOrDefault(6, 0),
        6 to shoal.getOrDefault(7, 0) + shoal.getOrDefault(0, 0),
        7 to shoal.getOrDefault(8, 0),
        8 to shoal.getOrDefault(0, 0),
    )
}

fun main() {
    val input = readInput(6)
    val shoal = input
        .flatMap { it.split(",") }
        .map { it.toInt() }
        .groupingBy { it }
        .eachCount()
        .mapValues { (_, count) -> count.toLong() }

    val days = 256
    var result: Map<Int, Long> = shoal
    for (day in 0 until days) {
        result = simulate(result)
    }
    println("Part 1 answer: ${result.values.sum()}")
}
