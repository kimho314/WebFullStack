package fastcampus.spring.batch.part4;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Long score;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private LocalDateTime updateDateTime;

    @Builder
    public Order(Long score, LocalDateTime updateDateTime) {
        this.score = score;
        this.updateDateTime = updateDateTime;
    }

    public Order addUser(User user) {
        this.user = user;
        List<Order> orders = this.user.getOrders();
        orders.add(this);
        this.user.setOrders(orders);
        return this;
    }
}
