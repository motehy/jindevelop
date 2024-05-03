package me.hyukjin.developer.auth;

import lombok.RequiredArgsConstructor;
import me.hyukjin.developer.member.entity.RefreshToken;
import me.hyukjin.developer.member.repository.RefreshTokenRepository;
import org.jboss.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    @Override
    @SuppressWarnings("deprecation")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 추출
        String username = token.getName();
        String password = (String) token.getCredentials();
        // 해당 회원 Database 조회
        CommonPrincipal userDetail = (CommonPrincipal) userDetailsService.loadUserByUsername(username);

//        String presentedPassword = authentication.getCredentials().toString();

        System.out.println(passwordEncoder.encode(password));
        if (!passwordEncoder.matches(password, userDetail.getPassword())) {
            throw new BadCredentialsException("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }

        // 아이디 정보로 Token생성
        TokenDto tokenDto = jwtUtil.createAllToken(userDetail.getEmail());

        // Refresh토큰 있는지 확인
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByEmail(userDetail.getEmail());

        // 있다면 새토큰 발급후 업데이트
        // 없다면 새로 만들고 디비 저장
        if(refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        }else {
            RefreshToken newToken = new RefreshToken(userDetail.getEmail(), tokenDto.getRefreshToken());
            refreshTokenRepository.save(newToken);
        }


        // 인증 성공 시 UsernamePasswordAuthenticationToken 반환
        return new UsernamePasswordAuthenticationToken(userDetail, "", userDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
