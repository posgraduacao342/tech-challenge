package api.techchallenge.infrastructure.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalAuthentication()
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers(   HttpMethod.POST, "/clientes").permitAll()
                        .requestMatchers(   HttpMethod.PATCH, "/clientes/**").permitAll()
                        .requestMatchers(   HttpMethod.POST, "/pagamentos/**").permitAll()
                        .requestMatchers(   HttpMethod.GET, "/pedido/**").permitAll()
                        .requestMatchers(   HttpMethod.GET, "/produtos").permitAll()
                        .requestMatchers(   HttpMethod.POST, "/pedidos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pedidos/fila").authenticated()
                        .requestMatchers(HttpMethod.GET, "/pedidos/{pedidoId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produtos/porCategoria").permitAll()
                        .requestMatchers(HttpMethod.GET, "/health-check").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}