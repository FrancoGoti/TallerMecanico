package com.TP.TallerMecanico.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.TP.TallerMecanico.servicio.UsuarioServiceImplementacion;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @SuppressWarnings("unused")
    @Autowired
    private UsuarioServiceImplementacion userDetailsService;

    @SuppressWarnings("removal")

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/login", "/register").permitAll() // Permitir acceso a las páginas de login y registro
                .requestMatchers("/styles.css", "/static/**", "/fondo-pantalla.jpg").permitAll() // Permitir acceso a los recursos estáticos
                .anyRequest().authenticated()          // Cualquier otra solicitud requiere autenticación
        )
        .formLogin(formLogin ->
            formLogin
                .loginPage("/login")  // La URL de la página de login personalizada
                .loginProcessingUrl("/login") // URL para procesar el inicio de sesión
                .defaultSuccessUrl("/", true) // Redirige a la página de inicio después del inicio de sesión exitoso
                .permitAll()          // Permitir el acceso a la página de login
        )
        .logout(logout ->
            logout.permitAll()
        )
        .csrf().disable()  // Deshabilitar CSRF para evitar problemas en solicitudes de recursos estáticos (solo para pruebas)
        .headers(headers -> headers.frameOptions().sameOrigin()); // Asegurarse que las cabeceras no interfieran

    return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(); // Usa BCrypt para encriptar contraseñas
    }
}