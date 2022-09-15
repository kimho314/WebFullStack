package main

import "fmt"

func main() {
	i := 100

	if i >= 120 {
		fmt.Println("grater than 120")
	} else if i >= 100 && i < 120 {
		fmt.Println("less than 120 and greater than 100")
	} else if i < 100 && i >= 50 {
		fmt.Println("grater than 50 and less than 100")
	} else {
		fmt.Println("less than 50")
	}
}
