package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * @RequiredArgsConstructor 을 사용하면 생성자 코드를 사용하지않아도 자동으로 인덱션 해준다.
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/


    //회원가입
    @Transactional   //(readOnly = false) 가 적용되어 있음
    public Long join(Member member) {
        validateDuplicataMember(member);   //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicataMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new RuntimeException("이미 존재하는 회원입니다");
        }
    }
    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
