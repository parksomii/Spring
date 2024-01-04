package hello.hellospring;
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean  //스프링 빈에 등록하라는 뜻
    public MemberService memberService() {
        return new MemberService(memberRepository);  //MemberService가 빈에 등록됨
    }

    /* 이거 없이 AOP 클래스에서@Component 써도 됨
    @Bean
    public TimeTraceAop timeTraceAop(){  //aop가 등록돼서 쓰이는 것
        return new TimeTraceAop();
    }
    */

    //@Bean
    //public MemberRepository memberRepository() {
    //return new MemoryMemberRepository();
    //return new JdbcMemberRepository(dataSource);
    //return new JdbcTemplateMemberRepository(dataSource);
    //return new JpaMemberRepository(em);
}
