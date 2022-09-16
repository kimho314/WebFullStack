package main

import (
	"fmt"
	"unicode/utf8"
)

func main() {
	// "", ``
	// char형이 존재하지 않음 -> rune(int32)형 문자 코드로 표현

	var str1 string = "c\\go_study\\src"
	var str2 string = `c\go_study\src`

	fmt.Println(str1)
	fmt.Println(str2)

	var str3 string = "hello"
	var str4 string = "안녕하세요."

	// 문자열의 byte 값
	fmt.Println(len(str3)) // len은 문자열의 byte!
	fmt.Println(len(str4))

	// 문자열의 실제 길이
	fmt.Println(utf8.RuneCountInString(str3))
	fmt.Println(utf8.RuneCountInString(str4))
	fmt.Println(len([]rune(str4)))
}
