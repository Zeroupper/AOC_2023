import java.io.File
import kotlin.math.max
import kotlin.math.min
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {

        val fileName = "data.txt"
        val lines = File(fileName).readLines()

        val numRows = lines.size
        val numColumns = lines[0].length
        val grid = Array(numRows) { CharArray(numColumns) }

        for ((i, value) in lines.withIndex()) {
            for (j in lines[i].indices) {
                grid[i][j] = lines[i][j]
            }
        }

        fun isPartNumber(i: Int, j: Int) : Boolean {
            fun isSymbol(c : Char) : Boolean {
                return !(c == '.' || c.isDigit())
            }

            val minRow = max(0, i - 1)
            val minCol = max(0, j - 1)
            val maxRow = min(numRows - 1, i + 1)
            val maxCol = min(numColumns - 1, j + 1)

            return isSymbol(grid[minRow][j]) ||
                    isSymbol(grid[maxRow][j]) ||
                    isSymbol(grid[i][minCol]) ||
                    isSymbol(grid[i][maxCol]) ||
                    isSymbol(grid[minRow][minCol]) ||
                    isSymbol(grid[minRow][maxCol]) ||
                    isSymbol(grid[maxRow][minCol]) ||
                    isSymbol(grid[maxRow][maxCol])
        }

        var currentNum = ""
        var totalSum = 0
        var isValid = false

        for (i in 0..(numRows-1)) {
            for (j in 0..(numColumns-1)) {

                if (grid[i][j].isDigit()) {
                    currentNum.plus(grid[i][j])
                    if(!isValid && isPartNumber(i,j)) {
                        isValid = true
                    }
                }
                else {
                    if(isValid && currentNum.isNotEmpty()) {
                        totalSum += currentNum.toInt()
                    }
                    currentNum = ""
                    isValid = false
                }
            }
        }

        println("sum -> $totalSum")
    }
    println("elapsed time -> $time ms")
}
