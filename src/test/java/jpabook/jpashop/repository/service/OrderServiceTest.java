package jpabook.jpashop.repository.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    //상품 주문

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception{
        Member member = new Member();
        member.setName("지누");
        member.setAddress(new Address("안양", "비산동", "947-1234"));
        em.persist(member);

        Book book = new Book();
        book.setAuthor("똥덩어리");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);


        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);


        assertEquals("상품주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류의 수가 정확해야한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문가격은 가격 * 주문수량이다.", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수만큼 재고가 줄어야한다.", 8, book.getStockQuantity());

    }

    //주문 취소
    @Test
    public void 주문_취소() throws Exception{

    }

    //재고수량 초과
    @Test
    public void 상품주문_재고수량_초과() throws Exception{

    }

}