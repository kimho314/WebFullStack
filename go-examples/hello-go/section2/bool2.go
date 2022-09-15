package main

import "fmt"

func main() {
	fmt.Println("ex1 : ", true && true)
	fmt.Println("ex1 : ", true && false)
	fmt.Println("ex1 : ", false && false)
	fmt.Println("ex1 : ", true || true)
	fmt.Println("ex1 : ", true || false)
	fmt.Println("ex1 : ", false || false)
	fmt.Println("ex1 : ", !true)
	fmt.Println("ex1 : ", !false)

	num1 := 15
	num2 := 17

	fmt.Println("ex2 : ", num1 < num2)
}
