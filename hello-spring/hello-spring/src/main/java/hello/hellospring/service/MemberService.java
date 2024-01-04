package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**회원 가입**/
    public Long join(Member member) {

        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member);  // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        }finally { /**AOP**/
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) { // 중복 여부 판별
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // NULL이 아니라 어떤 값이 있으면 동작
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**전체 회원 조회**/
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {  /**AOP**/
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}