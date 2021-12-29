package com.revature.services;

import java.util.List;


import com.revature.exceptions.EmployeeAlreadyExistsException;
import com.revature.models.Users;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;

public class UserService {
	
	private UserDao ud = new UserPostgres();
	
	public List<Users> getAllUsers(){
		return ud.getAllEmployee();
		
	}
	
	public Users addEmployee(Users employee) throws EmployeeAlreadyExistsException {
		Users newEmp = this.getEmployeeByUsername(employee.getUsername());
		if(newEmp != null) {
			throw new EmployeeAlreadyExistsException();
		}
		return ud.addEmployee(employee);
	}
	
	public Users getEmployeeByUsername(String username) {
		List<Users> employees = getAllUsers();
		for(Users e : employees) {
			if(e.getUsername().equals(username)) {				
				return e;
			}
		}
		return null;
	}
	
	public Users getEmployeeById(int id) {
		Users e = ud.getEmployeeById(id);
//		if (e != null) {
//			e.setPassword(null);
//		}
		
		return e;
	}
	
	
	public boolean updateEmployee(Users e) {
		Users eUpdate = ud.getEmployeeById(e.getId());
		if(e.getFirstName() !=null && !e.getFirstName().trim().isEmpty()) {
			eUpdate.setFirstName(e.getFirstName());
		}
		
		if(e.getLastName() !=null && !e.getLastName().trim().isEmpty()) {
			eUpdate.setLastName(e.getLastName());
		}
		
		if(e.getUsername() !=null && !e.getUsername().trim().isEmpty()) {
			eUpdate.setUsername(e.getUsername());
		}
		
		if(e.getPassword() !=null && !e.getPassword().trim().isEmpty()) {
			eUpdate.setPassword(e.getPassword());
		}
		if(e.getEmail() !=null && !e.getEmail().trim().isEmpty()) {
			eUpdate.setEmail(e.getEmail());
		}
				
			return 	ud.updateEmployee(eUpdate);
		
	}
	
	
	
	
	public String login(String username, String password)  {
		Users employee = getEmployeeByUsername(username);
		String token = null;
		
			if (employee!=null &&  employee.getPassword().equals(password)) {
				
				token = employee.getId() + ":" + employee.getRole().getId();
				}
			return token;
		}
	
	

}
