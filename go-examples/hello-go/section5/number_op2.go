package main

import "fmt"

func main() {
	var num1 uint8 = 16
	var num2 uint8 = 125

	fmt.Println(num1 + num2)
	fmt.Println(num1 - num2)
	fmt.Println(num1 / num2)
	fmt.Println(num1 % num2)
	fmt.Println(uint16(num1) * uint16(num2))
	fmt.Println(num1 << 2)
	fmt.Println(num1 >> 2)
	fmt.Println(^num1)

}
