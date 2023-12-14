package com.bos.server.config;

import com.bos.server.oauth.filter.IdPwAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
               // .requestMatchers(new AntPathRequestMatcher("/users/login")).permitAll()
//                .requestMatchers(new AntPathRequestMatcher("/oauth2/login")).permitAll()
                .anyRequest().authenticated());
        http.formLogin(withDefaults());
       /* http.formLogin(e -> e
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/oauth2/login")
                .loginProcessingUrl("/oauth2/sign-in")
                .permitAll()
        );*/
        //http.with(new AuthDsl(), withDefaults());
        http.httpBasic(AbstractHttpConfigurer::disable);
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(daoAuthenticationProvider);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   /* public static class AuthDsl extends AbstractHttpConfigurer<AuthDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

            DefaultLoginPageGeneratingFilter loginPageGeneratingFilter = http
                    .getSharedObject(DefaultLoginPageGeneratingFilter.class);
            http.addFilterBefore(new IdPwAuthenticationFilter("/users/sign-in", authenticationManager), UsernamePasswordAuthenticationFilter.class);
        }
    }*/
}