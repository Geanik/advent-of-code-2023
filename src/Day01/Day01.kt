fun main() {
    val spelledOutNumbers = listOf(
        "1" to "one",
        "2" to "two",
        "3" to "three",
        "4" to "four",
        "5" to "five",
        "6" to "six",
        "7" to "seven",
        "8" to "eight",
        "9" to "nine",
    )

    fun part1(input: List<String>): Int =
        input.sumOf { line ->
            line.first { it.isDigit() }.plus(
                line.last { it.isDigit() }.toString()
            ).toInt()

        }


    fun part2(input: List<String>): Int =
        input.sumOf { line ->
            val indexesOfSpelledOutNumbersFirst = spelledOutNumbers.mapNotNull { (digit, digitName) ->
                val index = line.indexOf(digitName, ignoreCase = true)

                if (index >= 0) {
                    digit to index
                } else null
            }

            val indexesOfSpelledOutNumbersLast = spelledOutNumbers.mapNotNull { (digit, digitName) ->
                val index = line.lastIndexOf(digitName, ignoreCase = true)

                if (index >= 0) {
                    digit to index
                } else null
            }

            val indexOfFirstDigit = line.indexOfFirst { it.isDigit() }.takeIf { it >= 0 }
            val x =
                indexesOfSpelledOutNumbersFirst.filter { indexOfFirstDigit == null || it.second < indexOfFirstDigit!! }
                    .minByOrNull { it.second }
            val firstDigit = x?.first ?: line[indexOfFirstDigit!!].toString()

            val indexOfLastDigit = line.indexOfLast { it.isDigit() }.takeIf { it >= 0 }
            val y = indexesOfSpelledOutNumbersLast.filter { indexOfLastDigit == null || it.second > indexOfLastDigit!! }
                .maxByOrNull { it.second }
            val lastDigit = y?.first ?: line[indexOfLastDigit!!].toString()

            firstDigit.plus(lastDigit).toInt()
        }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput(name = "Day01", testInput = true)
    check(part2(testInput) == 281)

    val input = readInput(name = "Day01")
    part1(input).println()
    part2(input).println()
}
