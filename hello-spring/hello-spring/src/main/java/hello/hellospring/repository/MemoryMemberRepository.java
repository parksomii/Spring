package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
public class MemoryMemberRepository implements MemberRepository {

    private  static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0l;  // sequence는 0,1,2 이런 방법으로 키값을 생성해 주는 역할
    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //ID 세팅하기
        store.put(member.getId(),member);   //store에 저장 == map에 저장
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //store에서 get해서 id 넣기, NULL을 감싸기 위해 Optional 사용
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  //같은 이름이면 필터링 되는 것
                .findAny();  //찾으면 반환됨, 없으면 NULL 반환
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
