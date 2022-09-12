package main

import "fmt"

func main() {
	//실수(부동소수점)
	//float32(7 digits), float64(15 digits)

	var num1 float32 = 0.14
	var num2 float32 = .75647
	var num3 float32 = 442.0378374
	var num4 float32 = 10.0

	var num5 float32 = 14e6
	var num6 float64 = .156875e+3
	var num7 float64 = 5.32521e-3

	fmt.Println("ex1 : ", num1)
	fmt.Println("ex2 : ", num2)
	fmt.Println("ex3 : ", num3)
	fmt.Println("ex4 : ", num4)

	fmt.Println("ex5 : ", num5)
	fmt.Println("ex6 : ", num6)
	fmt.Println("ex7 : ", num7)

	fmt.Println("ex8 : ", num4-0.1)
	fmt.Println("ex9 : ", float32(num4-0.1))
	fmt.Println("ex10 : ", float64(num4-0.1))
}
