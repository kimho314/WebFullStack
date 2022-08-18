package main

import (
	"fmt"
	"time"
)

func main() {
	fmt.Println("Hello World!!!!!")
	// fmt.Println("time : ", time.Now())

	// this function returns the present time
	current_time := time.Now().UTC()
	fmt.Println("current_time : ", current_time)

	// using inbuilt format constants
	// shown in the table above
	fmt.Println("ANSIC: ", current_time.Format(time.ANSIC))
	fmt.Println("UnixDate: ", current_time.Format(time.UnixDate))
	fmt.Println("RFC1123: ", current_time.Format(time.RFC1123))
	fmt.Println("RFC3339Nano: ", current_time.Format(time.RFC3339Nano))
	fmt.Println("RubyDate: ", current_time.Format(time.RubyDate))

	fmt.Println("local current_time : ", current_time.Local())
	fmt.Println("current_time im unix milli : ", current_time.UnixMilli())

	loc, err := time.LoadLocation("Asia/Seoul")
	if err != nil {
		panic(err)
	}
	timeInKST := current_time.In(loc)
	fmt.Println("timeInKST : ", timeInKST)
}
