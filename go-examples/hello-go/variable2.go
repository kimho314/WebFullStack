package main

import "fmt"

func main() {
	var (
		name string = "machine learning"
		height int32
		weight float32
		isRunning bool
	)

	height = 250
	weight = 350.50
	isRunning = true

	fmt.Println("name : ", name, "height : ", height, "weight : ", weight, "isRunning : " , isRunning)
	fmt.Printf("name : %s, height : %d, weight : %f, isRunning : %t\n", name, height, weight, isRunning)
}