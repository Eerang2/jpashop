package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;
    private String etc;
    private String name;
    private int price;
    private int stockQuantity;
}
