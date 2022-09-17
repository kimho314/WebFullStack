package main

import "fmt"

func main() {
	//utf-8처리, 유니코드 문자 집합 -> 한글 3byte, 영어 1byte

	var str1 string = "GoLang"
	var str2 string = "world"
	var str3 string = "고프로그래밍"

	fmt.Println(str1[0], str1[1], str1[2], str1[3], str1[4], str1[5])
	fmt.Println(str2[0], str2[1], str2[2], str2[3], str2[4])
	fmt.Println(str3[0], str3[1], str3[2], str3[3], str3[4], str3[5])

	fmt.Printf("%c %c %c %c %c %c\n", str1[0], str1[1], str1[2], str1[3], str1[4], str1[5])
	fmt.Printf("%c %c %c %c %c\n", str2[0], str2[1], str2[2], str2[3], str2[4])
	fmt.Printf("%c %c %c %c %c %c\n", str3[0], str3[1], str3[2], str3[3], str3[4], str3[5])

	constStr := []rune(str3)
	fmt.Printf("%c %c %c %c %c %c\n", constStr[0], constStr[1], constStr[2], constStr[3], constStr[4], constStr[5])

	for i, char := range str3 {
		fmt.Printf("%c(%d) ", char, i)
	}
	fmt.Println()
}
