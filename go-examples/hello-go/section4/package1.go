package main

import (
	"fmt"
	"os"
)

func main() {
	//패키지 : 모듈화 -> 재사용성 증가
	//Go : 패키지 단위의 독립적이고 작은 단위로 개발
	//패키지 이름 = 디렉토리 이름
	//네이밍 : 소문자 private, 대문자 public
	//Go : main 패키지는 프로그램의 시작점으로 인식

	var name string

	fmt.Println("name : ")
	fmt.Scanf("%s", &name)
	fmt.Fprintf(os.Stdout, "Hi! %s\n", name)
}
