package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@EntityListeners(value = {UserEntityListener.class})
@Table(name = "user", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User extends BaseEntity {
    @Id
    // strategy 졸류
    // IDENTITY : Mysql, MariaDB
    // SEQUENCE : Oracle, Postgre, Mssql
    // TABLE : DB 종류에 상관없이 ID값 관리하는 별도 DB 생성함
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;

    // Enum 활용시 Enumerated annotation 활용해야함
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    /*@Column(name = "crtdat", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;*/

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;

    @Transient // DB에 반영 안됨
    private String testData;

    /*@PrePersist // before insert
    @PreUpdate
    @PreRemove
    @PostPersist
    @PostUpdate
    @PostRemove
    @PostLoad   // after select*/
}
