package com.nagarro.productcatalogsystem.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nagarro.productcatalogsystem.service.CustomUserDetailsService;

@Configuration("SecurityConfiguration")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
@Autowired
private JwtAuthenticationFilter jwtFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
        
        
        
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("user")).roles("USER");
        
        
        
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
//                .cors()
//                .disable()
                .authorizeRequests()
                .antMatchers("/token").permitAll()
//                .antMatchers("api/v1/admin").hasRole("ADMIN")
//                .antMatchers("api/v1/user").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
              
//                .httpBasic(); 
        httpSecurity.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
    }
    

    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();

    }
    
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
    	return super.authenticationManagerBean();
    }
}
