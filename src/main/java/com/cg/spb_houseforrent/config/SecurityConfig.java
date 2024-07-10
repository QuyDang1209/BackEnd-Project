package com.cg.spb_houseforrent.config;

import com.cg.spb_houseforrent.config.impl.CustomAccessDeniedHandler;
import com.cg.spb_houseforrent.config.impl.JwtAuthenticationTokenFilter;
import com.cg.spb_houseforrent.config.impl.RestAuthenticationEntryPoint;
import com.cg.spb_houseforrent.config.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private UserService userService;

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/**").permitAll()
//                      .requestMatchers("/api/auth/**").permitAll()
//                                .requestMatchers(HttpMethod.GET,"/api/user/active/**").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/user").permitAll()
////                        .requestMatchers(HttpMethod.PATCH,"/api/user/change-active").hasAnyAuthority("admin")
////                        .requestMatchers(HttpMethod.PATCH,"/api/user/change-password/**").hasAnyRole()
////                        .requestMatchers(HttpMethod.PATCH,"/api/user/edit/**").hasAnyRole()
////                        .requestMatchers(HttpMethod.GET,"/api/user/**").hasAnyRole()
//                        .requestMatchers(HttpMethod.POST,"/api/user/create").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/api/user").hasAnyAuthority("ROLE_ADMIN")
//                                .anyRequest().authenticated()

                )
                .exceptionHandling(customizer -> customizer.accessDeniedHandler(customAccessDeniedHandler()))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
