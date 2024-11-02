package edu.eci.cvds.Tasks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.eci.cvds.Tasks.service.MongoUserDetailsService;

/**
 * Configuración de seguridad web para la aplicación.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http el objeto {@link HttpSecurity} para configurar la seguridad HTTP.
     * @return la cadena de filtros de seguridad configurada.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll());
        return http.build();
    }

    /**
     * Proporciona una implementación de {@link UserDetailsService} para cargar
     * detalles del usuario.
     *
     * @return una instancia de {@link MongoUserDetailsService}.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new MongoUserDetailsService();
    }

    /**
     * Proporciona un codificador de contraseñas.
     *
     * @return una instancia de {@link BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}