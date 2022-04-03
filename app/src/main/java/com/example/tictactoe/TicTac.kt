package com.example.tictactoe

import kotlin.math.abs

fun main() {
    print("Enter cells: ")
    var inpStr = ""
    do {
        inpStr = readln()
    } while (inpStr.length != 9)
    if (inpStr.length == 9) {
        fieldPrint(inpStr)
        check(inpStr)
    }
}

fun fieldPrint(str: String) {

    var str = str.replace("_" , " ")

    println(
        """
    ---------        
    | ${str[0]} ${str[1]} ${str[2]} |
    | ${str[3]} ${str[4]} ${str[5]} |
    | ${str[6]} ${str[7]} ${str[8]} |
    ---------""".trimIndent()
    )
}

fun check(str: String) {
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


    for (i in str) {
        if (i == 'X') x++
        if (i == 'O') o++
    }
    val sp = 9 - x - o
    val result = abs(x - o)

    for (i in winsPositions) {
        if (i == playerX) xWinCount++
        if (i == playerO) oWinCount++
    }
    val winCount = xWinCount + oWinCount

    if (result > 1 || winCount > 1) {
        println("Impossible")
    } else if (xWinCount == 1 && result <= 1) {
        println("X wins")
    } else if (oWinCount == 1 && result <= 1) {
        println("O wins")
    } else if (sp == 0 && winCount == 0) {
        println("Draw")
    } else if (sp > 0 && winCount == 0) {
        println("Game not finished")
    }

}
