/**
 * 
 */
package com.example.demo.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author Devarshi Pandya
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private UserDetailsService jwtUserDetailsService;
	
	@Value("${publicURI}")
	private String publicURI;
	private String[] urls;
	
	
	@PostConstruct
	public void init() {
		urls=publicURI.split(",");
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		//auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(jwtUserDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+urls);
		httpSecurity.csrf().disable()
		// dont authenticate this particular request
		//.authorizeRequests().antMatchers("/services/getOrders").permitAll().
		.authorizeRequests().antMatchers(urls).permitAll().
		// all other requests need to be authenticated
		anyRequest().authenticated().and().
		// make sure we use stateless session; session won't be used to
		// store user's state.
		/*exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().*/sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
	

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		//web.ignoring().antMatchers("/secure/authenticate/");
		web.ignoring().antMatchers(urls);
    }
}
