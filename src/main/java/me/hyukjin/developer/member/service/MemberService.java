package me.hyukjin.developer.member.service;

import me.hyukjin.developer.member.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member>  getAllMembers() throws Exception;
}
