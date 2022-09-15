package main

import (
	"example/hello/lib"
	"fmt"
)

var num int32

func init() {
	num = 30
	fmt.Println("Main init start!")
}

func main() {
	fmt.Println("10 보다 큰 수 : ", lib.CheckNum(num))
}
