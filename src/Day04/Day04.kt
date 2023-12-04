fun main() {
    fun matchingNumbersToPoints(nrOfMatchingNumbers: Int): Int {
        return when (nrOfMatchingNumbers) {
            0 -> 0
            1 -> 1
            else -> {
                var result = 1
                for (i in 1 until nrOfMatchingNumbers) {
                    result *= 2
                }
                result
            }
        }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val (_, numbers) = line.split(": ")
            val (winningNumbers, ourNumbers) = numbers.split("|").map { it.trim().split("\\s+".toRegex()) }

            val matchingNumbers = ourNumbers.filter { winningNumbers.contains(it) }
            matchingNumbersToPoints(matchingNumbers.count())
        }
    }


    fun part2(input: List<String>): Int {
        data class CardWithMatches(
            val index: Int,
            val nrOfMatches: Int,
            var nrOfProcessed: Int,
            var nrOfUnprocessed: Int
        )

        val cards = input.map { line ->
            val (cardPrefix, numbers) = line.split(": ")

            val (winningNumbers, ourNumbers) = numbers.split("|").map { it.trim().split("\\s+".toRegex()) }
            val nrOfMatches = ourNumbers.filter { winningNumbers.contains(it) }.size

            CardWithMatches(
                index = cardPrefix.split("\\s+".toRegex()).last().toInt(),
                nrOfMatches = nrOfMatches,
                nrOfProcessed = if (nrOfMatches == 0) 1 else 0,
                nrOfUnprocessed = if (nrOfMatches == 0) 0 else 1
            )
        }

        while (cards.any { it.nrOfUnprocessed > 0 && it.nrOfMatches > 0 }) {
            val card = cards.first { it.nrOfUnprocessed > 0 && it.nrOfMatches > 0 }

            cards.drop(card.index).take(card.nrOfMatches).forEach {
                if (it.nrOfMatches == 0) it.nrOfProcessed++
                else it.nrOfUnprocessed++

            }

            card.nrOfProcessed++
            card.nrOfUnprocessed--
        }

        return cards.sumOf { it.nrOfProcessed }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(name = "Day04", testInput = true)
    check(part2(testInput) == 30)

    val input = readInput(name = "Day04")
    part1(input).println()
    part2(input).println()
}
