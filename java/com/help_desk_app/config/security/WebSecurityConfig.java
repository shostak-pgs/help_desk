package com.help_desk_app.config.security;

import com.help_desk_app.entity.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.help_desk_app.config.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //private static final String UNPROTECTED_LOGIN_URL = "/";
    private static final String LOGOUT_URL = "/j_spring_security_logout";

    private static final String PROTECTED_TICKETS_URL = "/tickets";
    private static final String PROTECTED_USERS_URL = "/users";
    private static final String PROTECTED_TICKET_URL = "/tickets/**";

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final MySavedRequestAwareAuthenticationSuccessHandler successHandler;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder encoder;
    private final AuthFilter authFilter;

    public WebSecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                             MySavedRequestAwareAuthenticationSuccessHandler successHandler,
                             PasswordEncoder encoder,
                             AuthFilter authFilter,
                             CustomUserDetailsService customUserDetailsService) {
        this.successHandler = successHandler;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.encoder = encoder;
        this.authFilter = authFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public GlobalMethodSecurityConfiguration methodSecurityService() {
        return new GlobalMethodSecurityConfiguration();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders((Arrays.asList("*")));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
               // .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable() .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionFixation().migrateSession()
                .and()
                .authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                //.antMatchers(PROTECTED_TICKETS_URL, PROTECTED_TICKET_URL)
                .anyRequest().authenticated()
                .and()
                //.httpBasic()
                .logout().deleteCookies("JSESSIONID").invalidateHttpSession(true).logoutUrl(LOGOUT_URL).permitAll();

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

