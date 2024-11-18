// SecurityConfig.java

package com.recetas.recetas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home", "/css/**", "/js/**", "/crearReceta","/login").permitAll() // Permite acceso público a
                                                                                             // /home, login y recursos
                                                                                             // estáticos
                        .requestMatchers("/receta/detalles/**").authenticated() // Requiere autenticación para los
                                                                                // detalles de recetas
                        .anyRequest().authenticated() // Protege otras rutas
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de inicio de sesión personalizada
                        .defaultSuccessUrl("/home", true)
                        .permitAll())
                .logout(logout -> logout
                        .permitAll());
                

        return http.build();
    }
}
