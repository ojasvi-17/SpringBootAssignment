package com.nagarro.productcatalogsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productcatalogsystem.helper.JwtUtil;
import com.nagarro.productcatalogsystem.model.JwtRequest;
import com.nagarro.productcatalogsystem.model.JwtResponse;
import com.nagarro.productcatalogsystem.service.CustomUserDetailsService;
@RestController
public class JwtController {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
      System.out.println(jwtRequest);
      
      try
      {
    	  
    	  this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
    	  
      }
      catch(UsernameNotFoundException e)
      {
    	  e.printStackTrace();
    	  throw new Exception("Bad Credentials wrong username.");
      }
      catch(BadCredentialsException e)
      {
    	  e.printStackTrace();
    	  throw new Exception("Bad Credentials.");
      }
      
      UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
	  
      String token = this.jwtUtil.generateToken(userDetails);
      
      System.out.println("token:"+token);
      
      return ResponseEntity.ok(new JwtResponse(token));
	}

}
