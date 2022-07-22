package fastcampus.spring.batch.part4;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long age;

    private Long score;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Builder
    public User(String name, Long age, Long score, Rank rank) {
        this(0L, name, age, ObjectUtils.isEmpty(score) ? 0L : score, ObjectUtils.isEmpty(rank) ? Rank.NORMAL : rank);
    }

    public User(Long id, String name, Long age, Long score, Rank rank) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.rank = rank;
    }

    public User updateRank(Rank rank) {
        this.rank = rank;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder().append(name, user.name)
                .append(age, user.age)
                .append(score, user.score)
                .append(rank, user.rank)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name)
                .append(age)
                .append(score)
                .append(rank)
                .toHashCode();
    }
}
