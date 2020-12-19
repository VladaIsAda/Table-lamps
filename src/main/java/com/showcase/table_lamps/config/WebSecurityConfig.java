package com.showcase.table_lamps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**/*.css", "/**/*.js", "/**/*.scss",
                        "/**/*.png", "/**/*.svg", "/**/*.gif",
                        "/**/*.woff", "/**/*.woff2", "/**/*.ttf","/**/*.jpg").permitAll()
                .antMatchers("/", "/home", "/home/lamp/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/lampList/**","/**/redirect:/lampList").hasRole("ADMIN")
                .and()
                .formLogin();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("111")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(admin);
    }
}



