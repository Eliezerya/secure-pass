package com.eliezer.securepass.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    //uncomment if deploy to heroku
//    private CorsConfigurationSource configurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader(config.ALL);
//        config.addAllowedHeader(config.ALL);
//        config.addAllowedMethod(config.ALL);
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
    //uncomment if deploy to heroku

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //uncomment if deploy to heroku
//        http.cors().configurationSource(configurationSource()).and().requiresChannel().anyRequest().requiresSecure();
        //uncomment if deploy to heroku
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(
                "/refresh-token", "/authenticate",
                "api/v1/**",
                "/registrasi").permitAll();

        http.authorizeRequests().antMatchers("/login/**").permitAll();

        http.authorizeRequests().antMatchers( "/job/**").hasAnyAuthority("USER");

//        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().anyRequest().permitAll();
        //get get token dari endpoint login ke endpoint lainnya
        http.addFilterBefore(new CostumizeAuthorFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(new CostumizeFilter(authenticationManagerBean()));

    }
}