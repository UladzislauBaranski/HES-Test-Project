package com.gmail.vladbaransky.system.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;

import static com.gmail.vladbaransky.system.service.model.RoleEnum.Admin;
import static com.gmail.vladbaransky.system.service.model.RoleEnum.User;


@Configuration
@Profile("!test")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoderConfig passwordEncoder;

    public SecurityConfig(@Qualifier("appUserDetailsService") UserDetailsService userDetailsService, PasswordEncoderConfig passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new UserAccessDeniedHandler();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests().
                antMatchers("/", "/list").hasAnyRole(Admin.name(), User.name())
                .antMatchers("login")
                .permitAll()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .csrf().disable();
    }
}
