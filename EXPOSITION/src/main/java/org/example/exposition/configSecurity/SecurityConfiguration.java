package org.example.exposition.configSecurity;

import org.example.domaine.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Value("${secret}")
    private String secretProperties;
    @PostConstruct
    public void init() {
        Constants.SECRET = secretProperties;
    }
    @Autowired
    private UserDetailsService userDetailsService;
    BCryptPasswordEncoder passwordEncoder;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(Constants.FRONTURL));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Origin", "X-Requested-With", "Content-Type", "Accept"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        // Enable CORS and disable CSRF
        //TODO voir pour le CSRF disable
        http.cors(Customizer.withDefaults())
                .csrf().disable()
                .formLogin().disable();

        // set unauthorized requests exception handler
        http.exceptionHandling()
                        .authenticationEntryPoint(
                                (request, response, ex) -> {
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getLocalizedMessage());
                                    throw new UnauthorizedException();
                                }
                        )
                        .and();

        // set permissions on endpoints
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/login").permitAll()
                .antMatchers(HttpMethod.POST,"/users/register").permitAll()

                .antMatchers(HttpMethod.POST,"/movies/mass").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/series/mass").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST,"/usermovie").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/usermovie/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/usermovie/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/userserie").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/userserie/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE,"/userserie/**").hasRole("USER")

                .antMatchers(HttpMethod.GET, "/movies/*").permitAll()
                .antMatchers(HttpMethod.GET, "/series/*").permitAll()
                .antMatchers(HttpMethod.GET, "/movies").permitAll()
                .antMatchers(HttpMethod.GET, "/series").permitAll()

                .anyRequest().authenticated();

        // add JWT filter
        http.addFilterBefore(new JWTTokenFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilter(new JWTAuthenticationFilter (
                        authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))));

        return http.build();
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception{
        this.passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
