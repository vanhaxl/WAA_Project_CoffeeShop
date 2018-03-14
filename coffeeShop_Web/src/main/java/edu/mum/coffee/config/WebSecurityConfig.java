package edu.mum.coffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/logout", "/products", "/resources/**"
                            , "/img/**", "/"
                            ,"/js/**","/persons/signup").permitAll()
                .anyRequest().authenticated();
		http.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/").failureUrl("/login?error");
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/products");
        http.authorizeRequests()
                .antMatchers("/**/add", "/**/signup", "/**/admin", "/persons", "/orders").access("hasRole('ROLE_ADMIN')")
                .antMatchers(("/**/checkout")).access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");

        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}