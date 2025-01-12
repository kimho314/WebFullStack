package com.example.hello_jpa.member;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "member_sequence")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,
        allocationSize = 50 // 시퀀스 한 번 호출에 증가하는 수(성능 쇠적화에 사용됨. 데이터베이스 시퀀스 값이 하나씩 증가하도록 설정되어 있으면 이 값을 반드시 1로 설정해야 한다.)
)
@ToString
public class MemberSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
}
