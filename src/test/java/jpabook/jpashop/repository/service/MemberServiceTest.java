package jpabook.jpashop.repository.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("lee");

        Long saveId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(saveId));
        System.out.println(member);

    }

    @Test
    public void 회원_중복_예외() throws Exception {

        Member member1 = new Member();
        member1.setName("jinwoo");

        Member member2 = new Member();
        member2.setName("jinwoo");


        memberService.join(member1);
        try {
            memberService.join(member2);  //예외가 발생해야만 한다.
        } catch (IllegalStateException e) {
              return;
        }
            fail("예외가 발생 해야한다");


    }

}