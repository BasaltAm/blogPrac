package com.example.blogprac.config;
import com.example.blogprac.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toStaticResources;


@RequiredArgsConstructor
@Configuration
@RestControllerAdvice
public class WebSecurityConfig {
    @Autowired
    private final UserDetailService userService;
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) ->  web.ignoring()
                .requestMatchers(toStaticResources().atCommonLocations())
                .requestMatchers(("/mysql-console/**"));
    }


//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers("/login", "/signup", "/user"))
//                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
//                        .loginPage("/login").defaultSuccessUrl("/articles"))
//                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/login").permitAll()).csrf((csrf)->csrf.disable());
//        return http.build();
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
    http
            .authorizeRequests().requestMatchers("/login", "/signup", "/user").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                    .loginPage("/login").defaultSuccessUrl("/articles", true)
                    .permitAll())
            .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                    .logoutSuccessUrl("/login").invalidateHttpSession(true).permitAll()).csrf((csrf)->csrf.disable());
    return http.build();
}

    @Bean
    public AuthenticationManager authenticationManager(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);

        return providerManager;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}