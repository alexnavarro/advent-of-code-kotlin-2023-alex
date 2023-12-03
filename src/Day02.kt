import java.security.InvalidParameterException
import kotlin.math.max

fun main() {
    data class Set(val red: Int, val green: Int, val blue: Int)
    data class Game(val id: Int, val sets: List<Set>)

    fun parse(input: List<String>): List<Game> = input.map {
        val (game: String, sets: String) = it.split(":")
        val id: Int = "Game (\\d+)".toRegex().find(game)?.groupValues?.get(1)?.toIntOrNull() ?: 0
        if (id == 0) throw InvalidParameterException("Game id should be greater than 0")

        sets.split(";").map { value ->
            val red: Int = "(\\d+) red".toRegex().find(value)?.groupValues?.get(1)?.toIntOrNull() ?: 0
            val green: Int = "(\\d+) green".toRegex().find(value)?.groupValues?.get(1)?.toIntOrNull() ?: 0
            val blue: Int = "(\\d+) blue".toRegex().find(value)?.groupValues?.get(1)?.toIntOrNull() ?: 0

            Set(red, green, blue)
        }.let { set ->
            Game(id, set)
        }
    }

    fun part1(input: List<String>): Int {
        val expectedRed = 12
        val expectedGreen = 13
        val expectedBlue = 14

        return parse(input).filter { game ->
            game.sets.none { set -> set.blue > expectedBlue || set.red > expectedRed || set.green > expectedGreen }
        }.sumOf {
            it.id
        }
    }

    fun part2(input: List<String>): Int {
        return parse(input).sumOf { game ->
            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0

            game.sets.forEach {
                maxRed = max(it.red, maxRed)
                maxGreen = max(it.green, maxGreen)
                maxBlue = max(it.blue, maxBlue)
            }

            maxRed * maxGreen * maxBlue
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)


    val input = readInput("Day02")
    val input2 = readInput("Day02")
    part1(input).println()
    part2(input2).println()
}



