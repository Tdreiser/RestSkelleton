package ru.nordclan.RestSkelleton.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.nordclan.RestSkelleton.entity.enums.Role;
import ru.nordclan.RestSkelleton.service.CustomUserDetailService;

/**
 * @author Shlokov Andrey
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/clients/**")
                .hasAnyAuthority(Role.ADMIN.getAuthority())
                .antMatchers(  "/user/**")
                .hasAnyAuthority(Role.ADMIN.getAuthority())
                .antMatchers("/request/**")
                .hasAnyAuthority(Role.OPERATOR.getAuthority(),Role.OPERATOR.getAuthority())
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
