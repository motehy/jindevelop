package me.hyukjin.developer.member.repository;

import me.hyukjin.developer.member.entity.Member;
import me.hyukjin.developer.member.payload.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    MemberDto.Member findByEmail(String email);
}
