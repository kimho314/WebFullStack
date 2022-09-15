package main

import (
	"fmt"
)

// invoked ahead of main function
func init() {
	fmt.Println("Init1 Method Start")
}

func init() {
	fmt.Println("Init2 Method Start")
}

func init() {
	fmt.Println("Init3 Method Start")
}

func init() {
	fmt.Println("Init4 Method Start")
}

func init() {
	fmt.Println("Init5 Method Start")
}

func main() {
	//init : invoked on load of package
	fmt.Println("main method start")
}
