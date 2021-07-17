package org.springframework.security.loadtesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
    protected void configure(HttpSecurity http) throws Exception { 
		
		http
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/public")
	        .permitAll()
	        .antMatchers("/private")
	        .authenticated()
	        .and()
	        .httpBasic();
    }
  
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("username")
            .password("{noop}password")
            .roles("USER");
    }
    
}