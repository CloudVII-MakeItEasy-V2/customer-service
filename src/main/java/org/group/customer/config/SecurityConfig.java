package org.group.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/customers/getAllCustomers").authenticated() // 需要身份验证
                        .anyRequest().permitAll() // 允许其他请求访问
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/google")
                        .defaultSuccessUrl("/api/customers/getAllCustomers", true)
                        .failureUrl("/login?error")
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
