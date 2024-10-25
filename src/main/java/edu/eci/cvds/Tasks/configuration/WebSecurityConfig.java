package edu.eci.cvds.Tasks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import edu.eci.cvds.Tasks.service.MongoUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login").permitAll() // Login accesible para todos
                        .requestMatchers("/tasks/**").permitAll() // Solo para usuarios con rol USER
                        .requestMatchers("/analytics/**").permitAll() // Solo para administradores con rol
                        .anyRequest().permitAll()) // Cualquier otra ruta requiere autenticación
                .formLogin((form) -> form
                                .loginPage("http://localhost:8080/auth")
                                .permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new MongoUserDetailsService(); // Implementación personalizada para MongoDB
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliza BCrypt para cifrar las contraseñas
    }
}