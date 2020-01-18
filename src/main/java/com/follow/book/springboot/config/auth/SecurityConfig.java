package com.follow.book.springboot.config.auth;

import com.follow.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 을 위해 비활성화
                .csrf().disable().headers().frameOptions().disable()
                .and()
                // URL별 권한 관리 설정 옵션 시작(antMatchers()를 통해, 각 URL 별 권한을 설정 한다)
                .authorizeRequests()
                .antMatchers("/", "/css/**", "images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 위 설정 이외의 URL 설정. 인증된 사용자들만 허용
                .anyRequest().authenticated()
                .and()
                // 로그 아웃 시, "/" 로 이동
                .logout().logoutSuccessUrl("/")
                .and()
                //OAuth2 로그인 기능 설정. 소셜 로그인 후, 후속 조치를 처리할 bean 등록
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);

    }
}
