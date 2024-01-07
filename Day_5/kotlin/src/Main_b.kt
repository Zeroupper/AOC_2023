import java.io.File
import kotlin.math.max

data class Range(val destination: Long, val source: Long, val length: Long) {
    override fun toString(): String = "($destination, $source, $length)"
}

fun main() {
    val fileName = "data_b.txt"
    val lines = File(fileName).readLines()
    var seeds = mutableListOf<Interval>()

    val allMapsList = mutableListOf<MutableList<Range>>()

    for (index in lines.indices) {
        val words = lines[index].split(" ")

        if (words.isEmpty()) continue

        if (words.last() == "map:") {
            allMapsList.add(mutableListOf())
            if (allMapsList.size > 1) {
                allMapsList[allMapsList.size - 2] =
                    allMapsList[allMapsList.size - 2].sortedBy { it.destination }.toMutableList()

//                val intervals = getIntersectedIntervals()
            }
            continue
        }

        when (index) {
            0 -> {
                for (i in 1 until words.size step 2) {
                    val start = words[i].toLong()
                    val end = words[i].toLong() + words[i + 1].toLong()
                    seeds.add(Interval(start, end))
                }
            }

            else -> {
                if (words.size == 3) {
                    val range = Range(words[0].toLong(), words[1].toLong(), words[2].toLong())
                    allMapsList[allMapsList.size - 1].add(range)
                }
            }
        }

        if (index == lines.size - 1) {
            allMapsList[allMapsList.size - 1] =
                allMapsList[allMapsList.size - 1].sortedBy { it.destination }.toMutableList()
        }
    }

//    println(getIntersectedRanges(Range(65, 72, 1), allMapsList[allMapsList.size-5]), )


    fun getMinimumSeeds(level: Int, currentRanges: List<Range>,) : List<Long> {
        val possibleSeeds = mutableListOf<Long>()

//        println("level, currentRanges -> $level, $currentRanges")

        if (level == 0) {
            val seedsAtLevelZero = mutableListOf<Long>()
//            println("level, currentRanges -> $level, $currentRanges")
            for (r in currentRanges) {
//                println("currentInterval -> ${Interval(r.source, r.source + r.length - 1)}")

                for (s in seeds) {
//                    println("s -> $s")
                    val maxStart = maxOf(r.source, s.start)
                    val minEnd = minOf(r.source + r.length - 1, s.end)

                    if(maxStart < minEnd) {
                        seedsAtLevelZero.add(maxStart)
                    }
                }
            }
            return seedsAtLevelZero
        } else {
            for (r in currentRanges) {
                if (level == allMapsList.size && possibleSeeds.isNotEmpty()) {
//                    print("continue")
                    continue
                }

                val intersectedRanges = getIntersectedRanges(r, allMapsList[level - 1])
                possibleSeeds += getMinimumSeeds(level - 1, intersectedRanges)
            }
        }
        return possibleSeeds
    }

    val lastRanges = getIntersectedRanges(Range(0,0, Long.MAX_VALUE), allMapsList[allMapsList.size-1])
    val possibleSeeds = getMinimumSeeds(allMapsList.size - 1, lastRanges)

    fun mapItems(items: List<Long>, map: List<Range>): List<Long> {
        return items.map { item ->
            map.firstOrNull { range ->
                item in range.source until (range.source + range.length)
            }?.let { range ->
                range.destination + (item - range.source)
            } ?: item
        }
    }

    fun mapToLocation(seeds: List<Long>, allMapsList: List<List<Range>>): List<Long> {
        var currentItems = seeds
        for (map in allMapsList) {
            currentItems = mapItems(currentItems, map)
        }
        return currentItems
    }

    val locations = mapToLocation(possibleSeeds, allMapsList)

    println("Minimum seed -> ${locations.min()}")
}