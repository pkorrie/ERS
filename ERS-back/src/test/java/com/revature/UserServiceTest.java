package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.exceptions.EmployeeAlreadyExistsException;
import com.revature.models.Users;
import com.revature.repositories.UserDao;
import com.revature.services.UserService;

@ExtendWith(MockitoExtension.class)

public class UserServiceTest{
	
	@Mock
	private UserDao ud;
	
	@InjectMocks
	private UserService us;
	
	@Test
	public void getAllEmployees() {
		us = new UserService();			
		
		
	}
	
	@Test	
	public void addEmployeeTestValid() {
	Users emp = new Users("username", "firstName", "lastName", "password", "email",2);
		
		Mockito.when(ud.addEmployee(new Users("username", "firstName", "lastName", "password","email", 2))).thenReturn(emp);
		
		Users expected = new Users("username", "firstName", "lastName", "password","email",2);
		
		Users actual;
		try {
			actual = us.addEmployee(new Users("username", "firstName", "lastName", "password","email",2));
			assertEquals(expected, actual);
		} catch (EmployeeAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test	
	public void updateEmployeeTestValid() {		
		Mockito.when(ud.updateEmployee(new Users("username", "firstName", "lastName", "password1","email", 2))).thenReturn(true);
		
		boolean expected = true;
		
		boolean actual;
		actual = us.updateEmployee(new Users("username", "firstName", "lastName", "password1","email",2));
		assertEquals(expected, actual);
		
	}
//	
	@Test	
	public void loginTest() {
		Users user = new Users("jwells","wells","james","wells","wells@email.com",2);
//		Mockito.when(ud.getEmployeeByUsername("test_username")).thenReturn(user);
		
		String expected = user.getId()+ ":" + user.getRole();
		UserService us = new UserService();
		String actual = us.login("jwells", "wells");
//		System.out.println("token "  +actual);
		
		assertEquals(expected, actual);
	}
	
	@Test 
	public void getByUsername() {
		UserService us = new UserService();
		@SuppressWarnings("unused")
		Users u = us.getEmployeeByUsername("jwells");
//		System.out.println(us.getEmployeeByUsername("jwells"));

	}
	
	
}