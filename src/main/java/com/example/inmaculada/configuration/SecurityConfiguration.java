package com.example.inmaculada.configuration;

import com.example.inmaculada.services.jwt.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final IUserService userService;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, IUserService userService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/category").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/category").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/category/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/category").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/product").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/product").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/product/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/product/search").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/product/category").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/product/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/user").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/images").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService.userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificación de contraseñas
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
