package com.pos.posproject.Controller;

import PoSproject.PoS.domain.Member;
import PoSproject.PoS.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private final memberService memberService;

    @Autowired //DI를 스프링 컨테이너에서 담당하게 하기 위한 어노테이션
    public MemberController(memberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String createForm() {
        return "members/newmember";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) { //회원가입
        try {
            Member member = new Member();
            member.setName(form.getName());
            member.setId(form.getUserid());
            member.setPasswd(form.getUserpw());

            memberService.join(member);

            if (member.getCode() != 0) {
                return "redirect:/"; //회원가입 끝나서 가입하기 누르면 home(root page)로 설정된 페이지로 돌아감
            } else {
                return "/members/new";
            }
        } catch (IllegalStateException e) {
            System.out.println("아이디 중복 에러 발생");
            return "/members/new";
        }
    }

    @GetMapping("/member_login") //로그인
    public String LoginForm(@ModelAttribute("LoginForm") String loginid, String loginpw) {
        return "/members/login";
    }

    @PostMapping("/login")
    public String Login(String loginid, String loginpw) {
        try {
            System.out.println("로그인함수 들어옴: " + loginid + " " + loginpw);
            Optional<Member> finded = memberService.findMemberById(loginid);
            if (finded.isEmpty()) {
                System.out.println("일치하는 회원이 없습니다");
                return "/members/login";
            } else if (loginpw.equals(finded.get().getPasswd()) == false) {
                System.out.println("비밀번호가 틀렸습니다");
                return "/members/login";
            } else {
                System.out.println("로그인 성공: " + loginid + " " + loginpw);
                return "redirect:/";
            }
        } catch (IllegalStateException e) {
            System.out.println("로그인 에러");
            return "/members/login";
        }
    }


    @GetMapping("/member_list")//모든 회원 조회
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/members_list";
    }


}
