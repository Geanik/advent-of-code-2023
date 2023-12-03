import kotlin.math.min

fun main() {
    val availableCubes: Map<String, Int> = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    fun part1(input: List<String>): Int {
        return input.sumOf { game ->
            val (gameName, draws) = game.split(": ")
            val gameId = gameName.removePrefix("Game ").toInt()

            val hasNotTooMuchCubes = draws.split("; ").all { draw ->
                draw.split(", ").all { cubesOfColor ->
                    val (amount, color) = cubesOfColor.split(" ")

                    availableCubes[color]!! >= amount.toInt()
                }
            }

            if (hasNotTooMuchCubes) {
                gameId
            } else 0
        }
    }


    fun part2(input: List<String>): Int {
        return input.sumOf { game ->
            val draws = game.split(": ").last()

            val minNumbers = draws.split("; ").flatMap { draw ->
                draw.split(", ").map { cubesOfColor ->
                    val (amount, color) = cubesOfColor.split(" ")
                    color to amount.toInt()
                }
            }.groupBy { it.first }
                .map { it.value.maxBy { it.second }.second }

            minNumbers.reduce { acc, i -> acc * i }
        }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput(name = "Day02", testInput = true)
    check(part2(testInput) == 2286)

    val input = readInput(name = "Day02")
    part1(input).println()
    part2(input).println()
}
