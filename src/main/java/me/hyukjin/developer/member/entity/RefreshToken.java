package me.hyukjin.developer.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@Table(name = "member_token")
@NoArgsConstructor
public class RefreshToken {

    @Id
    private String email;

    @NotBlank
    @Column(name= "refresh_token")
    private String refreshToken;

    public RefreshToken(String email, String token) {
        this.email = email;
        this.refreshToken = token;
    }

    public RefreshToken updateToken(String token) {
        this.refreshToken = token;
        return this;
    }
}