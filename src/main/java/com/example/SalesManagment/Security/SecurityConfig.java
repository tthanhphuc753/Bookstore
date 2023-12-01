package com.example.SalesManagment.Security;

import com.example.SalesManagment.DAO.UserRepository;
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
                                .antMatchers("/api/user/**").hasAuthority("ADMIN")
                                .antMatchers("/api/auth/**")
                                .permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .formLogin()
                                .loginPage("/api/auth/login")
                                .loginProcessingUrl("/api/auth/authenticate")
                                .usernameParameter("email")
                                .successHandler(new JwtAuthenticationSuccessHandler(jwtService, userrepos))
                                .failureUrl("/api/auth/login?error=true")
                                .permitAll()
                                .and()
                                .logout()
                                .logoutUrl("/api/auth/logout")
                                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                                })
                                .deleteCookies("JWT_TOKEN")  // Xóa cookie chứa JWT
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
