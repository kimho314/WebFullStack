package main

import "fmt"

func main() {
	// 32bit, 64bit, unsigned
	// 정수 : 8진수(0), 10진수, 16진수(0x)

	var num1 int = 34
	var num2 int = -7
	var num3 int = 0356
	var num4 int = 0x123

	fmt.Println("num1 : ", num1)
	fmt.Println("num2 : ", num2)
	fmt.Println("num3 : ", num3)
	fmt.Println("num4 : ", num4)
}
