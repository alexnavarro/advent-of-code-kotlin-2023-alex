fun main() {
    fun part1(input: List<String>): Int {
        return sumOfAllNumbers(input)
    }

    fun part2(input: List<String>): Int {
        var sum=0
        input.forEach{
            sum +=  extractDigitsAndStringNumbersFromLine(it)
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(sumOfAllNumbers(testInput) == 142)
    val testInput2 = readInput("Day01_2_test")
//    check(sumOfAllNumbers(testInput2) == 142)

    val input = readInput("Day01")
    val input2 = readInput("Day01_2")
    part1(input).println()
    part2(input2).println()
}

private fun sumOfAllNumbers(input: List<String>): Int {
    var result: Int = 0
    input.forEach {item ->
        val first = item.first { it.isDigit() }
        val last = item.last { it.isDigit() }

        result += "$first$last".toInt()
    }

    return result
}

fun subStringNumberThreeDigit(value:String):String? {
    return when(value){
        "one" -> "1"
        "two" -> "2"
        "six" -> "6"
        else -> null
    }
}

fun subStringNumberFourDigit(value:String):String? {
    return when(value){
        "four" -> "4"
        "five" -> "5"
        "nine" -> "9"
        else -> null
    }
}

fun subStringNumberFiveDigit(value:String):String? {
    return when(value){
        "three" -> "3"
        "seven" -> "7"
        "eight" -> "8"
        else -> null
    }
}


fun extractDigitsAndStringNumbersFromLine(elfLine:String):Int{
    val digitList= mutableListOf<String>()
    elfLine.forEachIndexed { index, it ->
        if(it.isDigit()){
            digitList.add(it.toString())
        }else {
            if((index + 3) <= elfLine.length){
                subStringNumberThreeDigit(elfLine.substring(index, index+3))?.let { digitList.add(it) }
            }
            if((index + 4) <= elfLine.length){
                subStringNumberFourDigit(elfLine.substring(index, index+4))?.let { digitList.add(it) }
            }
            if((index + 5) <= elfLine.length){
                subStringNumberFiveDigit(elfLine.substring(index, index+5))?.let { digitList.add(it) }
            }
        }
    }
    digitList.println()
//    digitList.removeAll { it=="0" }
    digitList.println()
    return if(digitList.size==1){
        (digitList.first() + digitList.first()).toInt()
    }else {
        (digitList.first() + digitList.last().toInt()).toInt()
    }
}



