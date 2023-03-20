import java.lang.IllegalArgumentException
import java.util.TreeMap

fun main(args: Array<String>) {
//    println("Hello World!")
//    println(max(1,2))
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//    println("Program arguments: ${args.joinToString()}")


//    if(isInLove()){
//        println("Yes In love with My wife")
//    }else{
//        println("WTF!!!!")
//    }


//    val languages = arrayListOf("Java")
//    languages.add("Kotlin")
//    println(languages.joinToString())

//    val person = Person(name = "Hoseop", isMarried = false)
//    person.isMarried = true
//    println("person info ${person.name}, is marrid ${person.isMarried}")

    val rectangle1 = Rectangle(width = 1, height = 1)
    val rectangle2 = Rectangle(width = 2, height = 3)
//    println("rectangle1 is square ${rectangle1.isSquare}, rectangle2 is square ${rectangle2.isSquare}")

//    println(Color.RED.rgb())

//    println(getWarmth(Color.VIOLET))

//    println(eval(Sum(Sum(Num(1),Num(2)), Num(4))))
    println(evalWithLogging(Sum(Sum(Num(1),Num(2)), Num(4))))

    println("=========")
//    for(i in 1..100){
//        print(i)
//        print(" ")
//    }
//    println()
//    println("=========")
//    for(i in 1 until 100){ // iterate from 1 to 99; 100 is excluded
//        print(i)
//        print(" ")
//    }

    iterateOverMap()
    println("==========")
    val list = arrayListOf("10","30","101")
    for((index, element) in list.withIndex()){
        println("$index: $element")
    }

    println("==========")
    val list2 = listOf(1,2,3)
//    println(joinToString(list2, "; ", "(", ")"))
    println(joinToString(collection = list2, separator = "; ", prefix = "(", postfix = ")"))
    println(joinToString(collection = list2))
}

//fun isInLove(): Boolean {
//    return true
//}

fun getWarmth(color: Color) = when(color){
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}

//fun max(x1: Int, x2: Int) : Int {
//    return if (x1 > x2) x1 else x2
//}

fun max(a: Int, b: Int) : Int = if (a>b) a else b

//fun eval(e: Expr): Int{
//    if(e is Num){
//        val n = e as Num
//        return n.value
//    }
//    if(e is Sum){
//        return eval(e.right)
//    }
//    throw IllegalArgumentException()
//}

fun eval(e: Expr): Int =
    when(e){
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun evalWithLogging(e: Expr): Int =
    when(e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("unknown expression")
    }

fun iterateOverMap(){
    val binaryReps = TreeMap<Char,String>()

    for(c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.code)
        binaryReps[c] = binary
    }

    for((letter, binary) in binaryReps){
        println("$letter = $binary")
    }
}


fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String{
    val result = StringBuffer(prefix)

    for((index, element) in collection.withIndex()){
        if(index > 0){
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}