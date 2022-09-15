package main

import "fmt"

func main() {
	//제어문 - switch
	//switch 뒤 표현식 생략 가능
	//case 뒤 표현식 사용 가능
	//자동 break 때문에 fallthrough 존해
	//type 분기 -> 값이 아닌 변수 type으로 분기 가능

	a := -7
	switch {
	case a < 0:
		fmt.Println(a, "는 음수")
	case a == 0:
		fmt.Println(a, "는 0")
	case a > 0:
		fmt.Println(a, "는 양수")
	}

	switch b := 27; {
	case b < 0:
		fmt.Println(b, "는 음수")
	case b == 0:
		fmt.Println(b, "는 0")
	case b > 0:
		fmt.Println(b, "는 양수")
	}

	switch c := "go"; c {
	case "go":
		fmt.Println("Go!")
	case "java":
		fmt.Println("Java!")
	default:
		fmt.Println("No matched value")
	}

	switch c := "go"; c + "lang" {
	case "golang":
		fmt.Println("go lang")
	case "java":
		fmt.Println("java")
	default:
		fmt.Println("None")
	}

	switch i, j := 20, 30; {
	case i < j:
		fmt.Println("i is less than j")
	case i == j:
		fmt.Println("i is equal to j")
	case i > j:
		fmt.Println("i is greater than j")
	}
}
