package me.hyukjin.developer.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginUserDetailsService loginUserDetailsService;

    private final JwtUtil jwtUtil;

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return new MessageDigestPasswordEncoder("SHA-512");
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
//    @Bean
//    public UserAuthenticationProvider userAuthProvider() {
//        UserAuthenticationProvider userProvider = new UserAuthenticationProvider();
//        userProvider.setUserDetailsService(loginUserDetailsService);
//        userProvider.setPasswordEncoder(passwordEncoder());
//        return userProvider;
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(userAuthProvider());
//    }



    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/main", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
//                .csrf(AbstractHttpConfigurer::disable)
                .formLogin()
//                    .loginPage("/login")
//                    .usernameParameter("email").passwordParameter("pwd")
                    .successHandler(new RefererRedirectionAuthenticationSuccessHandler())
                .and()
                .logout()
                .and()
                // 커스텀 필터를 ID/PW 기반으로 인증하는 기본 필터 앞에 넣어서 먼저 인증을 시도하게 함
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

    }
}
