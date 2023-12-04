fun main() {
    data class SymbolPosition(val x: Int, val y: Int)
    data class NumberPosition(val startX: Int, val endX: Int, val y: Int, val value: Int)

    fun part1(input: List<String>): Int {
        val symbolPositions = mutableListOf<SymbolPosition>()
        val numberPositions = mutableListOf<NumberPosition>()

        input.forEachIndexed { y, line ->
            var currentNumberStartX = 0
            val currentNumberStringBuilder = StringBuilder()

            line.forEachIndexed { x, char ->
                if (char.isDigit()) {
                    if (currentNumberStringBuilder.isEmpty()) {
                        currentNumberStartX = x
                    }

                    currentNumberStringBuilder.append(char)
                } else {
                    if (currentNumberStringBuilder.isNotEmpty()) {
                        numberPositions.add(
                            NumberPosition(
                                startX = currentNumberStartX,
                                endX = x - 1,
                                y = y,
                                value = currentNumberStringBuilder.toString().toInt(),
                            )
                        )

                        currentNumberStringBuilder.clear()
                    }

                    if (!char.isLetter() && char != '.') {
                        symbolPositions.add(SymbolPosition(x, y))
                    }
                }
            }

            if (currentNumberStringBuilder.isNotEmpty()) {
                numberPositions.add(
                    NumberPosition(
                        startX = currentNumberStartX,
                        endX = line.length - 1,
                        y = y,
                        value = currentNumberStringBuilder.toString().toInt(),
                    )
                )

                currentNumberStringBuilder.clear()
            }
        }

        val eligibleNumbers = numberPositions.filter { number ->
            symbolPositions.any { symbol ->
                val xMatches = symbol.x >= number.startX - 1 && symbol.x <= number.endX + 1
                val yMatches = symbol.y >= number.y - 1 && symbol.y <= number.y + 1

                xMatches && yMatches
            }
        }

        return eligibleNumbers.sumOf { it.value }
    }


    fun part2(input: List<String>): Int {
        val gearPositions = mutableListOf<SymbolPosition>()
        val numberPositions = mutableListOf<NumberPosition>()

        input.forEachIndexed { y, line ->
            var currentNumberStartX = 0
            val currentNumberStringBuilder = StringBuilder()

            line.forEachIndexed { x, char ->
                if (char.isDigit()) {
                    if (currentNumberStringBuilder.isEmpty()) {
                        currentNumberStartX = x
                    }

                    currentNumberStringBuilder.append(char)
                } else {
                    if (currentNumberStringBuilder.isNotEmpty()) {
                        numberPositions.add(
                            NumberPosition(
                                startX = currentNumberStartX,
                                endX = x - 1,
                                y = y,
                                value = currentNumberStringBuilder.toString().toInt(),
                            )
                        )

                        currentNumberStringBuilder.clear()
                    }

                    if (char == '*') {
                        gearPositions.add(SymbolPosition(x, y))
                    }
                }
            }

            if (currentNumberStringBuilder.isNotEmpty()) {
                numberPositions.add(
                    NumberPosition(
                        startX = currentNumberStartX,
                        endX = line.length - 1,
                        y = y,
                        value = currentNumberStringBuilder.toString().toInt(),
                    )
                )

                currentNumberStringBuilder.clear()
            }
        }

        return gearPositions.sumOf { gear ->
            val adjacentNumbers = numberPositions.filter { number ->
                val xMatches = gear.x >= number.startX - 1 && gear.x <= number.endX + 1
                val yMatches = gear.y >= number.y - 1 && gear.y <= number.y + 1

                xMatches && yMatches
            }

            if (adjacentNumbers.size == 2) {
                adjacentNumbers.first().value * adjacentNumbers.last().value
            } else 0
        }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput(name = "Day03", testInput = true)
    check(part2(testInput) == 467835)

    val input = readInput(name = "Day03")
    part1(input).println()
    part2(input).println()
}
