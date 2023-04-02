package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id // PK
    @GeneratedValue // auto increment
    @Column(name = "deilvery_id") // db field명과 entity class의 멤버변수 이름을 다르게 쓰고 싶을 때 -> @Column을 안쓰면? -> 이름이 같은걸로 매칭시킨다.
    private Long id; // deleveryId (snake case <-> camel case)

    @JsonIgnore
    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;   //READY, COMP
}