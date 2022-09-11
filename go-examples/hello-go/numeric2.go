package main

import "fmt"

func main() {
	var char1 byte = 72
	var char2 byte = 0110
	var char3 byte = 0x48

	fmt.Println("char1 : ", char1)
	fmt.Println("char2 : ", char2)
	fmt.Println("char3 : ", char3)

	var char4 rune = 50556
	var char5 rune = 0142574
	var char6 rune = 0xC57C

	fmt.Printf("%c %c %c\n", char4, char5, char6)
	fmt.Printf("%d %d %d\n", char4, char5, char6)
	fmt.Printf("%d %o %x\n", char4, char5, char6)
}
