package com.revature.repositories;

import java.util.List;

import com.revature.models.Users;


public interface UserDao {
	List<Users> getAllEmployee();
	Users getEmployeeById(int id);
	Users getEmployeeByUsername(String username);
	Users addEmployee(Users employee);
	boolean updateEmployee(Users employee);	
}
