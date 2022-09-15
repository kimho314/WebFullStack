package main

import "fmt"

func main() {
	//제어문(조건문)
	//반드시 boolean 검사 -> 1, 0 사용 불가
	//소괄호 사용하지 않음

	var a int = 20
	b := 20

	if a >= 15 {
		fmt.Println("greater than 15")
	}

	if b >= 25 {
		fmt.Println("grater than 25")
	}

	if c := 40; c >= 35 {
		fmt.Println("grater than 35")
	}
}
