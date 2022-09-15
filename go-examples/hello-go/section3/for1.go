package main

import "fmt"

func main() {
	for i := 0; i < 10; i++ {
		fmt.Println(i)
	}

	// inifite loop
	// o := 1
	// for {
	// 	fmt.Println(o)
	// 	o++
	// }

	loc := []string{"seoul", "busan", "cheonan"}
	for index, name := range loc {
		fmt.Println(index, ", ", name)
	}
}
