package me.inonecloud.config;

import me.inonecloud.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(@Qualifier("domainUserDetailsService") UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/login**", "/js/**", "/error**", "/css/**", "/img/**", "/signUp", "/swagger-ui.html/**", "/storages/**", "/v2/api-docs", "/webjars/**", "/swagger-resources/**", "https://cloud-api.yandex.net/**", "/actuator/**", "/auditevents/**").permitAll()
                    .antMatchers("/api/auth").permitAll()
                    .antMatchers("/api/user/signUp").permitAll()
                    .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//        http
//                .authorizeRequests()
//                    .antMatchers("/", "/login**", "/js/**", "/error**", "/css/**", "/img/**").permitAll()
//                    .antMatchers("/api/user/signUp").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin()
//                .loginPage("/").permitAll()
//                .failureUrl("/login?error")
//                .and()
//                .logout().permitAll()
//                .and()
//                .csrf().disable();

    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
