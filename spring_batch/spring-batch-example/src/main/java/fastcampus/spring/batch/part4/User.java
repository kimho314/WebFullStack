package fastcampus.spring.batch.part4;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long age;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Rank rank = Rank.NORMAL;

    private LocalDateTime localDateTime;

    @Builder
    public User(String name, Long age, LocalDateTime localDateTime) {
        this.name = name;
        this.age = age;
        this.localDateTime = localDateTime;
    }

    public User updateRank(Rank rank) {
        this.rank = rank;
        return this;
    }

    public User addOrder(Order order) {
        order.setUser(this);
        this.orders.add(order);
        return this;
    }


    public Long getTotalScore(String _year, String _month) {
        final int year = Integer.parseInt(_year);
        final Month month = Month.of(Integer.parseInt(_month));

        long sum = this.orders.stream()
                .filter(order -> {
                    LocalDateTime updateDateTime = order.getUpdateDateTime();
                    int orderYear = updateDateTime.getYear();
                    Month orderMonth = updateDateTime.getMonth();

                    if (year == orderYear && month == orderMonth) {
                        return Boolean.TRUE;
                    }
                    return Boolean.FALSE;
                })
                .mapToLong(Order::getScore)
                .sum();

        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder().append(name, user.name)
                .append(age, user.age)
                .append(rank.ordinal(), user.rank.ordinal())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name)
                .append(age)
                .append(rank)
                .toHashCode();
    }
}
