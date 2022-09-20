package main

import "fmt"

func main() {
	var str1 string = "GoLang"
	var str2 string = "World"

	// go -> 문자열 비교시 아스키 코드에 대한 사전식 비교를 함
	fmt.Println("ex1 : ", str1 == str2)
	fmt.Println("ex2 : ", str1 != str2)
	fmt.Println("ex3 : ", str1 > str2)
	fmt.Println("ex4 : ", str1 < str2)
}
