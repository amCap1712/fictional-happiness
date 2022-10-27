package aoc

import aoc.Utils.readInput
import kotlin.math.abs

fun main() {
    val input = readInput(7)
    val crabs = input
        .flatMap { it.split(",") }
        .map { it.toInt() }
        .groupingBy { it }
        .eachCount()

    val lowest = crabs.keys.min()
    val highest = crabs.keys.max()

    var minCost: Int = Int.MAX_VALUE
    val part1 = false

    val score: (Int, Int, Int) -> Int = if (part1) {
        posCheck, pos, count -> abs(posCheck - pos) * count
    } else {
        posCheck, pos, count ->
        val steps = abs(posCheck - pos)
        steps * (steps + 1) * count / 2
    }

    for (posCheck in lowest..highest) {
        val cost = crabs
            .map { score(posCheck, it.key, it.value) }
            .sum()
        if (cost < minCost) {
            minCost = cost
        }
    }

    println("Part 1 answer: $minCost")
}
