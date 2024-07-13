package com.project.server.configuration;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import
// org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import
// org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import com.project.server.configuration.JwtRequestFilter;
// import com.project.server.service.CustomUserDetailsService;

// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class SecurityConfig {

// private final JwtRequestFilter jwtRequestFilter;

// public SecurityConfig(CustomUserDetailsService userDetailsService,
// JwtRequestFilter jwtRequestFilter) {
// this.jwtRequestFilter = jwtRequestFilter;
// }

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http.csrf(csrf -> csrf.disable())
// .authorizeRequests(requests -> {
// try {
// requests
// .requestMatchers("/api/auth/**").permitAll() // Allow unauthenticated access
// to auth
// // endpoints
// .anyRequest().authenticated()
// .and()
// .sessionManagement(management -> management
// .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
// } catch (Exception e) {
// e.printStackTrace();
// }
// });

// http.addFilterBefore(jwtRequestFilter,
// UsernamePasswordAuthenticationFilter.class);

// return http.build();
// }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Bean
// public AuthenticationManager
// authenticationManager(AuthenticationConfiguration
// authenticationConfiguration)
// throws Exception {
// return authenticationConfiguration.getAuthenticationManager();
// }
// }
