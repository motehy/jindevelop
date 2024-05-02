package me.hyukjin.developer.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginUserDetailsService loginUserDetailsService;

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        return new MessageDigestPasswordEncoder("SHA-512");
    }
    @Bean
    public UserAuthenticationProvider userAuthProvider() {
        UserAuthenticationProvider userProvider = new UserAuthenticationProvider();
        userProvider.setUserDetailsService(loginUserDetailsService);
        userProvider.setPasswordEncoder(passwordEncoder());
        return userProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthProvider());
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin()
//                    .loginPage("/login")
                    .usernameParameter("email").passwordParameter("pwd")
                    .successHandler(new RefererRedirectionAuthenticationSuccessHandler())
                .and()
                .logout();

    }
}
