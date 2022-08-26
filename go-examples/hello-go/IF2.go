package main

import "fmt"

func main(){
	var a int = 50
	b := 70

	if a >= 65 {
		fmt.Println("greater than 65")
	} else {
		fmt.Println("less than 65")
	}

	if b >= 70 {
		fmt.Println("greater than 70")
	} else {
		fmt.Println("less than 65")
	}
}