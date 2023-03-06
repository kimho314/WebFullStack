fun main(args: Array<String>) {
    println("Hello World!")
    println(max(1,2))
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
    println("rectangle1 is square ${rectangle1.isSquare}, rectangle2 is square ${rectangle2.isSquare}")

//    println(Color.RED.rgb())

    println(getWarmth(Color.VIOLET))
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