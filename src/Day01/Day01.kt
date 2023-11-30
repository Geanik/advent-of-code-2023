fun main() {
    fun part1(input: List<String>): Int {
        var maxCalorieSum = 0
        var curCalorieSum = 0

        for (curCalories in input.map { it.toIntOrNull() }) {
            if (curCalories != null) {
                curCalorieSum += curCalories
            } else {
                maxCalorieSum = maxOf(maxCalorieSum, curCalorieSum)
                curCalorieSum = 0
            }
        }
        maxCalorieSum = maxOf(maxCalorieSum, curCalorieSum)

        return maxCalorieSum
    }

    fun part2(input: List<String>): Int {
        var curCalorieSum = 0
        val calSums = mutableListOf<Int>()

        for (curCalories in input.map { it.toIntOrNull() }) {
            if (curCalories != null) {
                curCalorieSum += curCalories
            } else {
                calSums.add(curCalorieSum)
                curCalorieSum = 0
            }
        }
        calSums.add(curCalorieSum)

        return calSums.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(name = "Day01", testInput = true)
    check(part1(testInput) == 24000)

    val input = readInput(name = "Day01")
    part1(input).println()
    part2(input).println()
}
