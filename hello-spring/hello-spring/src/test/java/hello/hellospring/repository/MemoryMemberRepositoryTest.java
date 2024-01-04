package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//테스트는 순서와 관계, 의존관계 없이 설계되어야 한다 -> 하나의 테스트가 완료되면 저장소를 비워야 함

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();


    @AfterEach  //콜백 메소드
    public void afterEach(){
        repository.clearStore();  //테스트가 실행되고 끝날 때마다 저장소를 지움
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);
        //then
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        //given
        //Member 객체 생성 및 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        // findByName 메소드를 호출하여 이름이 "spring1"인 회원을 찾고 결과를 가져옴
        Member result = repository.findByName("spring1").get();

        //then
        //검색된 회원과 기대값(member1)을 비교하여 확인
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2); //몇 개인지에 따라 돌아가서 2개가 아닌 isEqualTo(3)이러면 에러
    }

}
