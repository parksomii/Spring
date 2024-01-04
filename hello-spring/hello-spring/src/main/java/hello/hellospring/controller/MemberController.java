package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    /** 생성자에 Autowired 가 있으면 스프링이 스프링컨테이너에 있는 memberservice를 연결
    memberservice 가 순수한 자바 클래스라서 스프링이 인식을 못해 에러 발생
    해결 방법 : 자바 파일로 가서 MemberService 객체 위해 @Service 입력
    그러면 스프링이 인식을 해서 memberservice를 스프링 컨테이너에 등록할 수 있게 된다
    Repository에 가서 @repository를 입력하면 Autowired랑 서비스랑 연결이 된다
    **/
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";  //회원가입이 끝나서 홈화면으로 보내는 것
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> member = memberService.findMembers();
        model.addAttribute("members",member); //멤버 리스트를 모델에 담아서 화면(뷰 템플릿)에 넘김
        return "members/memberList";

    }

}
