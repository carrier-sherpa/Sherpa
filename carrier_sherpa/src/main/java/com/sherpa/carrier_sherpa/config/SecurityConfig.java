package com.sherpa.carrier_sherpa.config;

import com.sherpa.carrier_sherpa.domain.service.MemberDetailsServiceImpl;
import com.sherpa.carrier_sherpa.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
// Sucure 어노테이션 활성화, PreAuthorize, PostAutorize 어노테이션 활성화
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig{

    //    private final MemberDetailsServiceImpl memberDetailsService;
//    private final AuthSuccessHandler authSuccessHandler;
//    private final AuthFailureHandler authFailureHandler;

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/assets/**", "/h2-console/**", "/api/hello2");
//    }

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.userDetailsService(memberService);
        http
                .authorizeHttpRequests((auth) -> auth
//                        .anyRequest().authenticated()
// TODO: 페이지에 따른 권한 설정
                                .anyRequest().permitAll()
                )
                .formLogin()
                .loginPage("/member/login") // 로그인 페이지 URL 지정
                .loginProcessingUrl("/login/action"); // 로그인 처리 URL 지정
//                .successHandler(authSuccessHandler) // 성공 handler
//                .failureHandler(authFailureHandler); // 실패 handler
//                .loginPage("/members/login") // 로그인 페이지 설정
//                .loginProcessingUrl("/members/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 일반 로그인을 진행
//                .defaultSuccessUrl("/");
        return http.build();
    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
//        return builder.userDetailsService(memberDetailsService).passwordEncoder(passwordEncoder()).and().build();
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberDetailsService).passwordEncoder(passwordEncoder());
//    }
}
