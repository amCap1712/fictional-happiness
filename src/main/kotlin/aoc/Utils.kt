package aoc

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest


object Utils {
    /**
     * Reads lines from the given input txt file.
     */
    fun readInput(year: Int, day: Int) = File(Utils.javaClass.getResource("y$year/Day$day.txt")?.path!!).readLines()

    fun readInputAsInts(year: Int, day: Int) = readInput(year, day).map { it.toInt() }

}
