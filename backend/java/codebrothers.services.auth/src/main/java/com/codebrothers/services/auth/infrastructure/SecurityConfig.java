package com.codebrothers.services.auth.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;  
import org.springframework.security.config.annotation.web.builders.HttpSecurity;  
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Classe responsável por gerenciar as exceções de autorização
 *
 */
@Configuration  
@EnableGlobalMethodSecurity(prePostEnabled = true)  
public class SecurityConfig extends WebSecurityConfigurerAdapter {  
	
	@Autowired
	SecurityEncoder encoder;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	throws Exception {
	
		//necessário identificar o password com o id {bcrypt}
		//user {noop} para plain text sem criptografica
		PasswordEncoder pe = encoder.getPasswordEncoder();
		String adminPass = "{bcrypt}" + pe.encode("admin");
		String userPass = "{bcrypt}" + pe.encode("user");
		
		auth.
		inMemoryAuthentication()
		.withUser("user").password(userPass).roles("USER").and()
		.withUser("admin").password(adminPass).roles("USER","ADMIN");

	}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http        		
          .authorizeRequests()
          .antMatchers("/api/user/v1/**").hasAnyRole("ADMIN","USER").and()
          //.antMatchers("/rest/**").hasAnyRole("ADMIN","USER").and()         
          .httpBasic()
          .and()
          .csrf().disable();
    }
    
  
    
	/*
    protected void configure(final HttpSecurity http) throws Exception {
    	
    	http
    		.authorizeRequests()    		
	        //liberando acesso ao actuator
	        .antMatchers("/actuator/**").permitAll()
    		.antMatchers("/api/**").hasAnyRole("ADMIN", "USER")        
    		.antMatchers("api/user/**").hasAnyRole("ADMIN", "USER")
	    	.anyRequest().authenticated()
	        .and().formLogin();      
    	
    	http.antMatcher("/**")  
	        .authorizeRequests()  
	        .antMatchers("/").permitAll()
	        //liberando acesso ao actuator
	        .antMatchers("/actuator/**").permitAll()
	        //liberando acesso as apis para testes apenas
	        //.antMatchers("/api/**").permitAll()	        
	        .anyRequest().authenticated()
	        .and().formLogin();      	
    }  
*/

}
