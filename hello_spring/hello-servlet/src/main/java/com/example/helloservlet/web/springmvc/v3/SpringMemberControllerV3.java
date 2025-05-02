package com.example.helloservlet.web.springmvc.v3;

import com.example.helloservlet.domain.member.Member;
import com.example.helloservlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    //    @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String members(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "members";
    }

    //    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
        @RequestParam("username") String username,
        @RequestParam("age") int age,
        Model model
    ) {
        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);

        model.addAttribute("member", savedMember);

        return "save-result";
    }
}
