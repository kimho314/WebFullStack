package main

import (
	"fmt"
	"strings"
)

func main() {
	str1 := "hoseop"
	str2 := "kim"

	fmt.Println("ex1 : ", str1+"."+str2)

	strSet := []string{} // 슬라이스 선언
	strSet = append(strSet, str1)
	strSet = append(strSet, str2)
	fmt.Println("ex2 : ", strings.Join(strSet, "."))
}
