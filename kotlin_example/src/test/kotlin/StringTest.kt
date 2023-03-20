import org.junit.jupiter.api.Test

class StringTest {
    @Test
    fun stringTest1(){
        println("12.345-6.A".split("\\.|-".toString()))
    }

    @Test
    fun stringTest2(){
        parsePath("/Users/yole/kotlin-book/chapter.adoc")

        val kotlinLogo = """|  //
                           .| //
                           .|/  \
        """
        println(kotlinLogo.trimMargin("."))
    }

    private fun parsePath(path: String){
        val directory = path.substringBeforeLast("/")
        val fullName = path.substringAfterLast("/")

        val fileName = fullName.substringBeforeLast(".")
        val extension = fullName.substringAfterLast(".")

        println("Dir: $directory, name: $fileName, ext: $extension")
    }
}