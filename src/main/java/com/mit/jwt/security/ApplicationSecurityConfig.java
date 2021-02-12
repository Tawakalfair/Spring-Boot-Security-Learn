package com.mit.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.mit.jwt.security.ApplicationUserRole.ADMIN;
import static com.mit.jwt.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                antMatchers("/","index","/css/*","/js/*").permitAll().
                antMatchers("/api/**").hasRole(STUDENT.name()).
                anyRequest().
                authenticated().
                and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userIqbal = User.builder()
                .username("iqbal")
                .password(passwordEncoder.encode("iqbal123"))
                .roles(STUDENT.name())
                .build();

        UserDetails userLinda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("linda123"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(
                userIqbal,
                userLinda
        );
    }
}
