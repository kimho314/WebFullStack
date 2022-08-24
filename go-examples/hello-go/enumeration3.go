package main

import "fmt"

func main(){
	const (
		_ = iota //스킵처리 가능
		A 
		B
		C
	)

	const (
		_ = iota + 0.75 * 2
		DEFAULT
		SILVER
		_
		PLATINUM
	)

	fmt.Println(DEFAULT,SILVER,PLATINUM)
}