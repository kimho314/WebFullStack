package main

import (
	"example/hello/lib"
	"fmt"
)

func main() {
	var i int32 = 30
	var ret bool
	ret = lib.CheckNum(i)
	fmt.Println(ret)
}
