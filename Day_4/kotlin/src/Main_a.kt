import java.io.File
import kotlin.math.pow

fun main() {
    val fileName = "data_b.txt"
    val lines = File(fileName).readLines()

    val winningCards = mutableListOf<List<String>>()
    val ownedCards = mutableListOf<List<String>>()

    var totalSum = 0.0

    for (i in lines.indices) {
        var numberOfMatches = 0.0
        val parts = lines[i].split("|")

        if (parts.size == 2) {
            val winningNumbers = parts[0].split(" ").filter { it.isNotBlank() && it.all { char -> char.isDigit() } }
            val ownedNumbers = parts[1].split(" ").filter { it.isNotBlank() && it.all { char -> char.isDigit() } }

            winningCards.add(winningNumbers)
            ownedCards.add(ownedNumbers)

            for (ownedNumber in ownedNumbers) {
                if(ownedNumber in winningNumbers) numberOfMatches += 1
            }
        }

        if(numberOfMatches == 0.0) continue
        totalSum += 2.0.pow(numberOfMatches - 1)
    }

    println(totalSum.toInt())
}