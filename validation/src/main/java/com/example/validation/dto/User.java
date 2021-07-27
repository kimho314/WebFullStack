package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @NotBlank
    private String name;

    @Max(value = 100)
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. xxx-xxxx-xxxx")
    private String phoneNumber;

    @YearMonth
//    @Size(min = 6, max = 6)
    private String reqYearMonth;    // yyyyMMdd

//    @AssertTrue(message = "yyyyMM 형식에 맞지 않습니다")
//    public boolean isReqYearMonthValidation(){
//        try{
//            LocalDate localDate = LocalDate.parse(getReqYearMonth()+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
//        }catch (Exception e){
//            return false;
//        }
//
//        return true;
//    }
}
