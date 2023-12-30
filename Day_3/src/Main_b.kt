import java.io.File
import kotlin.math.max
import kotlin.math.min

data class Position constructor(var x: Int, var y: Int) {}

fun main() {
    val fileName = "data_b.txt"
    val lines = File(fileName).readLines()

    val numRows = lines.size
    val numColumns = lines[0].length
    val grid = Array(numRows) { CharArray(numColumns) }

    for (x in 0..lines.size-1) {
        for (y in lines[x].indices) {
            grid[x][y] = lines[x][y]
        }
    }

    val gearsAdjacentToStar = mutableMapOf<String, List<Int>>()

    fun addGear(starPosition: String, currentNumber: Int) {
        val currentGears = gearsAdjacentToStar[starPosition] ?: listOf()
        gearsAdjacentToStar[starPosition] = currentGears + currentNumber
    }

    fun getBoundingRectangle(pos: Position, numberLength: Int) : List<Position> {
        val boundingRectangle = mutableListOf<Position>()

        val startX = max(0, pos.x - 1)
        val endX = min(numColumns - 1,pos.x + numberLength)
        val startY = max(0,pos.y - 1)
        val endY = min(pos.y + 1, numRows - 1)

        println("startX -> $startX")
        println("endX -> $endX")
        println("startY -> $startY")
        println("endY -> $endY")

        for (x in startX..endX) {
            for (y in startY..endY) {
                if (y == pos.y && x in pos.x..pos.x + numberLength-1) continue
                boundingRectangle.add(Position(x, y))
            }
        }
        return boundingRectangle
    }

    fun savePossibleGears(currentNum: Int, boundingRectangle: List<Position>) {
        fun isStar(c : Char) : Boolean {
            return c == '*'
        }

        for (position in boundingRectangle) {

            var y = position.x
            var x = position.y

            if(isStar(grid[x][y])) {
                addGear("x${x}y$y", currentNum)
            }
        }
    }

    var currentNum = ""
    var coord : Position? = null
    var totalSum = 0

    for (x in 0..(numRows-1)) {
        for (y in 0..(numColumns-1)) {

            if (grid[x][y].isDigit()) {
                if(coord == null) coord = Position(y,x)

                currentNum += grid[x][y]
            }
            else {
                if(coord != null) {
                    println("currentNum -> $currentNum")
                    var boundingRectangle = getBoundingRectangle(coord, currentNum.length)
                    savePossibleGears(currentNum.toInt(), boundingRectangle)
                }
                coord = null
                currentNum = ""
            }
        }
    }

    for ((k, v) in gearsAdjacentToStar) {
        if(v.size == 2) {
            println("k, v -> $k, $v")
            totalSum += v[0] * v[1]
        }
    }

    println("sum -> $totalSum")
}
