package me.hyukjin.developer.auth;

import lombok.RequiredArgsConstructor;
import me.hyukjin.developer.member.payload.MemberDto;
import me.hyukjin.developer.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDto.Member member =  memberRepository.findByEmail(email);
        if(member != null){
            return MemberPrincipal.create(member);
        }
        return null;
    }
}
