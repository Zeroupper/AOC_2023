import java.io.File
import kotlin.math.pow

fun main() {
    val fileName = "data_b.txt"
    val lines = File(fileName).readLines()

    val winningCards = mutableListOf<List<String>>()
    val ownedCards = mutableListOf<List<String>>()

    var totalScratchCards = 0

    val scratchCards : MutableMap<Int, Int> = mutableMapOf()

    lines.forEachIndexed { index, line ->
        var numberOfMatches = 0
        val parts = line.split("|")

        if (parts.size == 2) {
            val winningNumbers = parts[0].split(" ").filter { it.isNotBlank() && it.all { char -> char.isDigit() } }
            val ownedNumbers = parts[1].split(" ").filter { it.isNotBlank() && it.all { char -> char.isDigit() } }

            winningCards.add(winningNumbers)
            ownedCards.add(ownedNumbers)

            numberOfMatches = ownedNumbers.count { it in winningNumbers }

            val newScratchCards = index+2..numberOfMatches+index+1

            for (j in newScratchCards) {
                scratchCards[j] = scratchCards.getOrDefault(j, 1) + scratchCards.getOrDefault(index + 1, 1)
            }
        }
    }
    totalScratchCards = scratchCards.values.sum()

    println("Total number of scratch cards -> $totalScratchCards")
}