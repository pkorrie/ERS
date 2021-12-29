package com.revature.controllers;

import java.util.List;


import com.revature.exceptions.EmployeeAlreadyExistsException;
import com.revature.models.Users;
import com.revature.services.UserService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;


public class ManagerController {
	private static UserService us = new UserService();
	
	
	public static void getAllEmployees(Context ctx) {
		String token = ctx.header("Authorization");
		
		if (token.split(":")[1] =="1") {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}
		
		
		List<Users> emps =us.getAllUsers();
		
		ctx.json(emps);
		ctx.status(HttpCode.OK);
	}
	
	public static void registerEmployee(Context ctx) {
		Users newUser;
		try {
			newUser = us.addEmployee(ctx.bodyAsClass(Users.class));
			if (newUser == null) {
				ctx.status(HttpCode.BAD_REQUEST);
			} else {
				ctx.status(HttpCode.CREATED);
			}
		} catch (EmployeeAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void getEmployeeById(Context ctx) {
		String token = ctx.header("Authorization");
		
		if (token.split(":")[1] !="1" ) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}
		
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		Users u = us.getEmployeeById(id);
		
		if(u !=null) {
			ctx.json(u);
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(HttpCode.NOT_FOUND);
		}
	}
	
	
	public static void updateEmployeeInfo(Context ctx) {

		String token = ctx.header("Authorization");
			
		int id = Integer.parseInt(token.split(":")[0]);
		
		Users u = ctx.bodyAsClass(Users.class);
		u.setId(id);
		
		if(us.updateEmployee(u)) {
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(400);
		}
		
	}
	

	
	
}
