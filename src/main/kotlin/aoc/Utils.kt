package aoc

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest


object Utils {
    /**
     * Reads lines from the given input txt file.
     */
    fun readInput(day: Int) = File(Utils.javaClass.getResource("Day$day.txt")?.path!!).readLines()

    fun readInputAsInts(day: Int) = readInput(day).map { it.toInt() }

    /**
     * Converts string to aoc.md5 hash.
     */
    fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

}