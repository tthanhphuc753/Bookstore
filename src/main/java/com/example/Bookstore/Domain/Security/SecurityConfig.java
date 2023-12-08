package com.example.Bookstore.Domain.Security;

import com.example.Bookstore.Domain.Security.JWTAuth.JwtAuthenticationFilter;
import com.example.Bookstore.Domain.Security.JWTAuth.JwtAuthenticationSuccessHandler;
import com.example.Bookstore.Domain.Security.JWTAuth.JwtService;
import com.example.Bookstore.Persistence.DAO.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    private final UserRepository userrepos;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(authorizeRequests -> {
                    try {

                        authorizeRequests
                                .antMatchers("/user/**","/admin/**").hasAuthority("ADMIN")
                                .antMatchers("/auth/**","/shopping-cart/**","/book/**")
                                .permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .formLogin()
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/authenticate")
                                .usernameParameter("email")
                                .successHandler(new JwtAuthenticationSuccessHandler(jwtService, userrepos))
                                .failureUrl("/auth/login?error=true")
                                .permitAll()
                                .and()
                                .logout()
                                .logoutUrl("/auth/logout")
                                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                                })
                                .deleteCookies("JWT_TOKEN")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/");

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
