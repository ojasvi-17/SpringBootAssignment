Project name: Productcatalogsystem

Description: Product catalog system that lets admin to create,update,read,delete the products and users to browse 

the product list with information like product name, brand, price, color etc. and lets the user to read the products 

from the database.

Author: Ojasvi

Packages : 

1. productcatalogsystem :-
	
	contains the main method(start of application)
	
2. productcatalogsystem.config
	
	contains ProductConfig Class which is used for writing static data to db
	
3. productcatalogsystem.controller(Business Logic Layer)
	
	contains the business logic for ADMIN(CRUD)/ USER(READ ONLY)
	
4. productcatalogsystem.model
	
	contains product model
	
5. productcatalogsystem.repo
	
	contains repository which is extending JPA repository

6. productcatalogsystem.security
	
	Contains Authorization logic for different roles
	
7. productcatalogsystem.service(Service layer)
	
	contains the actual logic for the proposed business layer 

URLs for different roles:

ADMIN : api/v1/admin

USER : api/v1/user

NOTE: no home url present

credentials for different roles:

ADMIN:
	
	username: admin
	password: admin

USER:
	
	username: user
	password: user