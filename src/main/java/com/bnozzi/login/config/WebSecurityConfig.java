package com.bnozzi.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.bnozzi.login.auth.CustomAuthenticationSuccessHandler;
import com.bnozzi.login.model.UserDTO;
import com.bnozzi.login.service.UserService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        @Autowired
        private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

        @Bean // configuracion de la autorizacion de las rutas
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf // deshabilitar la autenticacion csrf para los metodos post
                                                .disable())
                                .authorizeHttpRequests(authRequest -> authRequest
                                                .requestMatchers(HttpMethod.POST, "/api/users").permitAll() 
                                                //permite el acceso sin autenticacion a la creacion de usuarios
                                                .anyRequest().authenticated()) 
                                                // el resto de rutas requeriran authenticacion
                                                .httpBasic(withDefaults()) // configuracion basica de
                                .formLogin(formlogin->{
                                        formlogin.successHandler(customAuthenticationSuccessHandler); //logeo exitoso
                                        
                                }) 

                                .build();
        }

        @Autowired
        private UserService userService;

        @Bean // configuracion de los usuarios
        public UserDetailsService userDetailsService() {
                return username -> {
                        UserDTO userDTO = userService.findByEmail(username);
                        if (userDTO == null) {
                                throw new UsernameNotFoundException("Usuario no encontrado: " + username);
                        }

                        return User.builder().username(userDTO.getEmail()).password(userDTO.getPassword()).roles("USER")
                                        .build();
                };

        }

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
                return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                                .userDetailsService(userDetailsService()).and().build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return NoOpPasswordEncoder.getInstance(); // No apto para producción
        }

   

}