package me.hyukjin.developer.member.controller;

import me.hyukjin.developer.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/get/users", method = RequestMethod.GET)
    public String getUserAll() throws Exception {
        return memberService.getAllMembers().toString();
    }

}
