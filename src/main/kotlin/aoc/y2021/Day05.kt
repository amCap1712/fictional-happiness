package aoc.y2021

import aoc.Utils.readInput
import java.util.regex.Pattern

data class Point2D(val x: Int, val y: Int)

data class LineSegment(val start: Point2D, val end: Point2D) {
    private fun slope(): Double {
        return (end.y - start.y) * 1.0 / (end.x - start.x)
    }

    fun isVertical(): Boolean {
        return start.x == end.x
    }

    fun isHorizontal(): Boolean {
        return start.y == end.y
    }

    fun points(): List<Point2D> {
        val points = mutableListOf<Point2D>()
        if (isHorizontal()) {
            val xMin = minOf(start.x, end.x)
            val xMax = maxOf(start.x, end.x)
            for (x in xMin..xMax) {
                points.add(Point2D(x, start.y))
            }
        } else if (isVertical()) {
            val yMin = minOf(start.y, end.y)
            val yMax = maxOf(start.y, end.y)
            for (y in yMin..yMax) {
                points.add(Point2D(start.x, y))
            }
        } else {
            val p1: Point2D
            val p2: Point2D
            if (start.x <= end.x) {
                p1 = start
                p2 = end
            } else {
                p1 = end
                p2 = start
            }
            val slope: Int = slope().toInt()
            for (i in 0..(p2.x - p1.x)) {
                points.add(Point2D(p1.x + i, p1.y + i * slope))
            }
        }
        return points
    }
}

fun score(lines: List<LineSegment>, part1: Boolean): Int {
    val filtered = if (part1) {
        lines.filter { it.isHorizontal() or it.isVertical() }
    } else {
        lines
    }
    return filtered
        .flatMap { it.points() }
        .groupingBy { it }
        .eachCount()
        .filter { it.value > 1 }
        .size
}

fun main() {
    val input = readInput(2021, 5)
    val pattern = Pattern.compile("(\\d+),(\\d+)\\s+->\\s+(\\d+),(\\d+)")
    val lines = input.map {
        val match = pattern.matcher(it)
        match.find()
        val start = Point2D(match.group(1).toInt(), match.group(2).toInt())
        val end = Point2D(match.group(3).toInt(), match.group(4).toInt())
        LineSegment(start, end)
    }
    println("Part 1 Answer: ${score(lines, true)}")
    println("Part 2 Answer: ${score(lines, false)}")
}
