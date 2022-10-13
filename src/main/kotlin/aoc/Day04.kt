package aoc

import aoc.Utils.readInput

class Board(private val id: Int, private val board: List<MutableList<Int>>) {
    fun mark(n: Int): Boolean {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == n) {
                    board[i][j] = -1
                    return isComplete(i, j)
                }
            }
        }
        return false
    }

    fun isComplete(row: Int, col: Int) = isRowComplete(row) or isColComplete(col)

    private fun isRowComplete(row: Int): Boolean {
        for (i in board[row].indices) {
            if (board[row][i] != -1) {
                return false
            }
        }
        return true
    }

    private fun isColComplete(col: Int): Boolean {
        for (i in board.indices) {
            if (board[i][col] != -1) {
                return false
            }
        }
        return true
    }

    fun score(): Int {
        var sum = 0
        for (row in board) {
            for (x in row) {
                if (x != -1) {
                    sum += x
                }
            }
        }
        return sum
    }

    fun print() {
        for (row in board) {
            for (n in row) {
                val s = "%2d".format(n)
                print(s)
                print(" ")
            }
            println()
        }
        println()
    }
}

fun winFirst(numbers: List<Int>, boards: List<Board>): Int {
    var score = -1
    outer@for (n in numbers) {
        for (board in boards) {
            if (board.mark(n)) {
                score = board.score() * n
                break@outer
            }
        }
    }
    return score
}

fun winLast(numbers: List<Int>, boards: List<Board>): Int {
    var score = -1

    val copyOfBoards = mutableListOf<Board>()
    copyOfBoards.addAll(boards)

    for (n in numbers) {
        val itr = copyOfBoards.listIterator()
        var lastRemoved: Board? = null
        while (itr.hasNext()) {
            val board = itr.next()
            if (board.mark(n)) {
                itr.remove()
                lastRemoved = board
            }
        }
        if (copyOfBoards.isEmpty()) {
            score = lastRemoved?.score()?.times(n) ?: -1
            break
        }
    }
    return score
}

fun main() {
    val input = readInput(4)
    val numbers = input.first().split(",").map { it.toInt() }

    val boards = mutableListOf<Board>()

    var counter = 1

    for (i in 1 until input.size step 6) {
        // skip first line its empty
        val board = mutableListOf<MutableList<Int>>()
        for (j in i + 1 until i + 6) {
            board.add(input[j].split(" ").filter { it.trim().isNotBlank() }.map { it.toInt() }.toMutableList())
        }
        boards.add(Board(counter++, board))
    }

//    val score1 = winFirst(numbers, boards)
//    println("Part 1 Score is $score1")

    val score2 = winLast(numbers, boards)
    println("Part 2 Score is $score2")

}
