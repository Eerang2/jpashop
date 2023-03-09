package jpabook.jpashop.repository;

import org.springframework.util.StringUtils;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.script.ScriptEngine;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        //회원이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        return null;
    }

/**
 * criteria
 */
//   public List<Order> findAllByCriteria(OrderSearch orderSearch) {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
//        Root<Order> o = criteriaQuery.from(Order.class);
//        Join<Object, Object> m = o.join("member", JoinType.INNER);
//
//        List<Predicate> criteria = new ArrayList<>();
//
//        //주문 상태 검색
//        if (orderSearch.getOrderStatus() != null) {
//            Predicate status = criteriaBuilder.equal(o.get("status"), orderSearch.getOrderStatus());
//            criteria.add(status);
//        }
//
//        //회원 이름 검색
//        if (StringUtils.hasText(orderSearch.getMemberName())) {
//            Predicate name =
//                    criteriaBuilder.like(m.<String>get("naem"), "%" + orderSearch.getMemberName() + "%");
//            criteria.add(name);
//        }
//        criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[criteria.size()])));
//        TypedQuery<Order> query = em.createQuery(criteriaQuery).setMaxResults(1000);
//        return query.getResultList();
//    }
}
