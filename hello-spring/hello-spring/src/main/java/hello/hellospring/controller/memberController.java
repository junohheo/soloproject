package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class memberController {
    private final MemberService memberService;

    @Autowired //memberservice를 연결해준다
    public memberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
