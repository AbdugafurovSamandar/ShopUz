package com.company.config;

import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth", "/auth/**").permitAll()
                .antMatchers("/admin/create_operator").hasRole("ADMIN")
                .antMatchers("/admin/getList").hasRole("ADMIN")
                .antMatchers("/profile/change_pass").hasAnyRole("OPERATOR","ADMIN", "USER")
                .antMatchers("/profile/update_detail").hasAnyRole("OPERATOR","USER")
                .antMatchers("/admin/delete/*").hasRole("ADMIN")
                .antMatchers("/admin/change_status/*").hasRole("ADMIN")
                .antMatchers("/region/adm/*").hasRole("ADMIN")
                .antMatchers("/district/adm/*").hasRole("ADMIN")
                .antMatchers("/category/adm/*").hasRole("ADMIN")
                .antMatchers("/attach/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
        http.cors().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String md5 = MD5Util.getMd5(rawPassword.toString());
                return md5.equals(encodedPassword);
            }
        };

    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
