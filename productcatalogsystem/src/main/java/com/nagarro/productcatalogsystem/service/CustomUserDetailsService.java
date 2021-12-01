package com.nagarro.productcatalogsystem.service;

import java.util.ArrayList;

import com.nagarro.productcatalogsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.productcatalogsystem.model.CustomUserDetails;
//import com.nagarro.productcatalogsystem.model.User;
import com.nagarro.productcatalogsystem.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService  {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		  final User user=this.userRepository.findByUsername(username);

	        if(user==null){
	            throw new UsernameNotFoundException("User not found");
	        }
	        else{
	            return new CustomUserDetails(user);
	        }
//		if(username.equals("username"))
//		{
//			return new User("username","username",new ArrayList<>());
//		}
//		else
//		{
//			throw new UsernameNotFoundException("user not found");
//		}

	}
}
