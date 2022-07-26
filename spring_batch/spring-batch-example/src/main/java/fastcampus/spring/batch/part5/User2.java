package fastcampus.spring.batch.part5;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 100)
    private String name;

    private Long age;

    private LocalDateTime updateDateTime;

    @Builder
    public User2(Long id, String name, Long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
