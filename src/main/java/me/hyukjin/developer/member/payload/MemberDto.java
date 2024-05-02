package me.hyukjin.developer.member.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import java.security.Principal;
import java.util.Collection;

@NoArgsConstructor
public class MemberDto {

    @Getter
    @Setter
    public static class Member {

        private Long seq;

        private String email;

        private String name;

        private String pwd;

        public Member(Long seq, String email, String name, String pwd) {
            this.seq = seq;
            this.email = email;
            this.name = name;
            this.pwd = pwd;
        }
    }
}
