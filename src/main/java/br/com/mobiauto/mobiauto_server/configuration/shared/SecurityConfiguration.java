package br.com.mobiauto.mobiauto_server.configuration.shared;

import br.com.mobiauto.mobiauto_server.core.entity.Cargo;
import br.com.mobiauto.mobiauto_server.core.useCase.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/usuarios/**").hasAnyRole("ADMINISTRADOR", "PROPRIETARIO", "GERENTE")
                .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("ADMINISTRADOR", "PROPRIETARIO")
                .requestMatchers("/usuarios/**").hasAnyRole("ADMINISTRADOR", "PROPRIETARIO", "GERENTE")

                .requestMatchers(HttpMethod.GET, "/revendas/**").authenticated()
                .requestMatchers("/revendas/**").hasRole("ADMINISTRADOR")

                .requestMatchers("/clientes/**").authenticated()
                .requestMatchers("/veiculos/**").authenticated()
                .requestMatchers("/oportunidades/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

}
