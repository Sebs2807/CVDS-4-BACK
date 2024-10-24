package edu.eci.cvds.Tasks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import edu.eci.cvds.Tasks.service.MongoUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login").permitAll()   // Login accesible para todos
                .requestMatchers("/tasks/**").hasRole("USER")   // Solo para usuarios con rol USER
                .requestMatchers("/analytics/**").hasRole("ADMIN")  // Solo para administradores con rol ADMIN
                .anyRequest().authenticated())  // Cualquier otra ruta requiere autenticación
            .formLogin((form) -> form
                .loginPage("/login")
                .permitAll())
            .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MongoUserDetailsService();
    }
}
