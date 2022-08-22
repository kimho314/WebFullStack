package main

import "fmt"

func main() {
	const a, b int = 10, 20
	const (
		x, y int16 = 50, 90
		i, k       = "test", 7878
	)

	fmt.Println(a, b, x, y, i, k)
}
