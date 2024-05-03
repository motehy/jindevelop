package me.hyukjin.developer.member.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
