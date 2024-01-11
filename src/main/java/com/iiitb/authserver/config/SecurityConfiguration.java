package com.iiitb.authserver.config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers("/api/v1/auth/demo-controller/prof-ping").hasAnyAuthority("ROLE_PROFESSOR");
        http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers("/api/v1/auth/demo-controller/superadmin-ping").hasAuthority("ROLE_SUPERADMIN");
        http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers("/api/v1/auth/demo-controller/student-ping").hasAuthority("ROLE_STUDENT");
        http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers("/api/v1/auth/demo-controller/admin-ping").hasAuthority("ROLE_ADMIN");
        http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers("/api/v1/auth/register", "/api/v1/auth/authenticate").permitAll().anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}