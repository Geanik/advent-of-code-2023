fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(name = "Day01", testInput = true)
    check(part1(testInput) == 2)

    val input = readInput(name = "Day01")
    part1(input).println()
    part2(input).println()
}
