package main

import "fmt"

func main() {
	var str1 string = "GoLang"
	var str2 string = "World"

	fmt.Println("str1 : ", str1[0:3], str1[0])
	fmt.Println("str2 : ", str2[3:], str2[0])
	fmt.Println("str2 : ", str2[:4])
}
