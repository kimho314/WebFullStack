package main

import (
	"fmt"
)

// invoked ahead of main function
func init() {
	fmt.Println("Init Method Start")
}

func main() {
	//init : invoked on load of package
	fmt.Println("main method start")
}