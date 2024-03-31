package me.hyukjin.developer.member.service.impl;

import me.hyukjin.developer.member.entity.Member;
import me.hyukjin.developer.member.repository.MemberRepository;
import me.hyukjin.developer.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() throws Exception{
        return memberRepository.findAll();
    }
}
