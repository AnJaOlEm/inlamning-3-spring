package me.code.uppgift3projekt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityCfg {

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails user = User.withUsername();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http
                .csrf()
                    .disable()
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .build();



    }

}
