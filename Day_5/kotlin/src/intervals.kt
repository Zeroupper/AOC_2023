import kotlin.math.max
import kotlin.math.min

data class Interval(val start: Long, val end: Long){
    override fun toString(): String = "($start <-> $end)"
}

fun getIntersectedRanges(range: Range, ranges: List<Range>): List<Range> {
    val allRanges = mutableListOf<Range>()

    val sourceInterval = Interval(range.source, range.source + range.length -1)
//    println("sourceInterval -> $sourceInterval")

    val sortedRanges = ranges.filter {
        val interval = Interval(it.destination, it.destination + it.length-1)
        interval.start <= sourceInterval.end && interval.end >= sourceInterval.start
    }.sortedBy { it.destination }

//    println("sortedRanges -> $sortedRanges")

    if(sortedRanges.isEmpty()) {
        return listOf(Range(range.source, range.source, range.length))
    }

    var currentStart = sourceInterval.start

    for (r in sortedRanges) {
//        println("r -> $r")
        val begin = max(r.destination, sourceInterval.start)
        val end = min(r.destination + r.length-1, sourceInterval.end)

        val interval = Interval(begin, end)
//        println("interval $interval")
        if (currentStart < interval.start) {
            allRanges.add(Range(currentStart, currentStart, interval.start - currentStart))
        }
        val offset = r.destination - begin
        val newSource = r.source - offset

        allRanges.add(Range(begin, newSource, end-begin+1))
//        println("currentStart $currentStart")
        currentStart = interval.end + 1
    }

    if (currentStart <= sourceInterval.end) {
        allRanges.add(Range(currentStart, currentStart, sourceInterval.end-currentStart))
    }

    return allRanges
}

fun getIntersectedIntervals(originalI: Interval, intervals: List<Interval>): List<Interval> {
    val allIntervals = mutableListOf<Interval>()
    val sortedIntervals = intervals.filter {
        it.start < originalI.end && it.end > originalI.start
    }.sortedBy { it.start }

    println(sortedIntervals)

    var currentStart = originalI.start

    for (i in sortedIntervals) {
//        println("currentStart -> $currentStart")
//        println("i -> $i")
        if (i.start > currentStart) {
            allIntervals.add(Interval(currentStart, i.start - 1))
        }
        allIntervals.add(i)
        currentStart = i.end + 1
    }

    if (currentStart <= originalI.end) {
        allIntervals.add(Interval(currentStart, originalI.end))
    }

    return allIntervals
}