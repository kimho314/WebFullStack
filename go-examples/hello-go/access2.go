package main

import (
	checkUp "example/hello/lib"
	checkUp2 "example/hello/lib2"
	"fmt"
)

func main() {
	//별칭 사용
	//빈 식별자 사용

	fmt.Println("10보다 큰 수? : ", checkUp.CheckNum(11))
	fmt.Println("1000보다 큰 수? : ", checkUp2.CheckNum2(999))
}