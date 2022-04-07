package com.example.tictactoe

import java.util.*
import kotlin.math.abs

fun main() {

    val board = "         "
    val grid = arrayOf(
        arrayOf(board[0], board[1], board[2]),
        arrayOf(board[3], board[4], board[5]),
        arrayOf(board[6], board[7], board[8]),
    )

    fieldPrint(grid)
    while (true) {

        playerX(grid)
        if (check(arrToString(grid)) == "X wins") {
            println("X wins")
            break
        } else if (check(arrToString(grid)) == "O wins") {
            println("O wins")
            break
        } else if (check(arrToString(grid)) == "Draw") {
            println("Draw")
            break
        }
        playerO(grid)
        if (check(arrToString(grid)) == "X wins") {
            println("X wins")
            break
        } else if (check(arrToString(grid)) == "O wins") {
            println("O wins")
            break
        } else if (check(arrToString(grid)) == "Draw") {
            println("Draw")
            break
        }
    }
}

fun playerX(grid: Array<Array<Char>>): Array<Array<Char>> {
    var coords = gettingCords()

    var switch = true
    while (switch) {
        if (grid[coords[0]][coords[1]] != ' ') {
            println("This cell is occupied! Choose another one!")
            coords = gettingCords()
        } else {
            grid[coords[0]][coords[1]] = 'X'
            fieldPrint(grid)
            switch = false
        }
    }
    return grid
}

fun playerO(grid: Array<Array<Char>>): Array<Array<Char>> {
    var coords = gettingCords()

    var switch = true
    while (switch) {
        if (grid[coords[0]][coords[1]] != ' ') {
            println("This cell is occupied! Choose another one!")
            coords = gettingCords()
        } else {
            grid[coords[0]][coords[1]] = 'O'
            fieldPrint(grid)
            switch = false
        }
    }
    return grid
}

fun fieldPrint(mutList: Array<Array<Char>>) {
    println("-".repeat(9))
    mutList.forEach { println("| ${it.joinToString(" ")} |") }
    println("-".repeat(9))
}

fun arrToString(grid: Array<Array<Char>>): String {
    var result = ""
    for (i in grid.indices) {
        var j = 0
        while (j < grid[i]?.size) {
            result += grid[i][j].toString()
            j++
        }
    }
    return result
}

fun isNumeric(toCheck: String): Boolean {
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}

fun gettingCords(): List<Int> {
    val scanner = Scanner(System.`in`)
    var switch = true

    var long = 0
    var lat = 0

    do {
        print("Enter the coordinates: ")
        val coords = scanner.nextLine().split(" ")
        if (isNumeric(coords[0]) && isNumeric(coords[1])) {
            if (coords[0].toInt() > 3 || coords[1].toInt() > 3) {
                println("Coordinates should be from 1 to 3!")
            } else if (coords[0].toInt() <= 3 && coords[1].toInt() <= 3) {
                long = coords[0].toInt() - 1
                lat = coords[1].toInt() - 1
                switch = false
            }
        } else {
            println("You should enter numbers!")
        }
    } while (switch)

    val result = listOf(long, lat)
    return result
}


fun check(str: String): String {
    val winsPositions = listOf(
        listOf(str[0], str[1], str[2]),
        listOf(str[3], str[4], str[5]),
        listOf(str[6], str[7], str[8]),
        listOf(str[0], str[3], str[6]),
        listOf(str[1], str[4], str[7]),
        listOf(str[2], str[5], str[8]),
        listOf(str[2], str[4], str[6]),
        listOf(str[0], str[4], str[8]),
    )
    val playerX = listOf('X', 'X', 'X')
    val playerO = listOf('O', 'O', 'O')

    var xWinCount = 0
    var oWinCount = 0

    var x = 0
    var o = 0

    var result = "String"


    for (i in str) {
        if (i == 'X') x++
        if (i == 'O') o++
    }
    val sp = 9 - x - o
    val xoProportion = abs(x - o)

    for (i in winsPositions) {
        if (i == playerX) xWinCount++
        if (i == playerO) oWinCount++
    }
    val winCount = xWinCount + oWinCount

    if (xoProportion > 1 || winCount > 1) {
        result = "Impossible"
    } else if (xWinCount == 1 && xoProportion <= 1) {
        result = "X wins"
    } else if (oWinCount == 1 && xoProportion <= 1) {
        result = "O wins"
    } else if (sp == 0 && winCount == 0) {
        result = "Draw"
    } else if (sp > 0 && winCount == 0) {
        result = "Game not finished"
    }

    return result
}
