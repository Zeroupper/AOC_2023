import java.io.File

fun main() {
    data class Range(val destination: Long, val source: Long, val length: Long)

    val fileName = "data.txt"
    val lines = File(fileName).readLines()
    var seeds = mutableListOf<Long>()

    val allMapsList = mutableListOf<MutableList<Range>>()

    for (index in lines.indices) {
        val words = lines[index].split(" ")

        if(words.isEmpty()) continue

        if(words.last() == "map:") {
            allMapsList.add(mutableListOf())
            continue
        }

        when (index) {
            0 -> {
                seeds.addAll(words.slice(1..<words.size).map {
                    it.toLong()
                })
            }
            else -> {
                if(words.size == 3) {
                    val range = Range(words[0].toLong(), words[1].toLong(), words[2].toLong())
                    allMapsList[allMapsList.size - 1].add(range)
                }
            }
        }
    }

    for (i in allMapsList.indices) {
        val newSeeds = mutableListOf<Long>()
        for (j in seeds.indices) {
            var haveFoundInterval = false
            for (k in allMapsList[i].indices) {
                if(seeds[j] >= allMapsList[i][k].source && seeds[j] < allMapsList[i][k].source + allMapsList[i][k].length) {
                    val diff = allMapsList[i][k].source - allMapsList[i][k].destination
                    newSeeds.add(seeds[j] - diff)
                    haveFoundInterval = true
                    break
                }
            }
            if(!haveFoundInterval) {
                newSeeds.add(seeds[j])
            }
        }
        seeds = newSeeds
    }
    println(seeds.min())
}