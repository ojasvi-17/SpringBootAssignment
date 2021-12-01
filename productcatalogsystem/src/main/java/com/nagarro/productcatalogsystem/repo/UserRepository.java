package com.nagarro.productcatalogsystem.repo;
	
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nagarro.productcatalogsystem.model.User;


public interface UserRepository extends JpaRepository<User,Long> {
	
    public User findByUsername(String userName);
   
}

