package com.revature.controllers;

import com.revature.models.Users;
import com.revature.services.UserService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class EmployeeController {
	
	private static UserService us = new UserService();
	
	
	public static void getEmployeeById(Context ctx) {
		String token = ctx.header("Authorization");

		int id = Integer.parseInt(token.split(":")[0]);
		Users u = us.getEmployeeById(id);
		
		if (u != null) {
			ctx.json(u);
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(404);
			ctx.status(HttpCode.NOT_FOUND);
		}
		
	}
	
	
	public static void updateEmployee(Context ctx) {
		String token = ctx.header("Authorization");
		
		int id = Integer.parseInt(token.split(":")[0]);	
		
		Users u = ctx.bodyAsClass(Users.class);
		u.setId(id);
		
		if (us.updateEmployee(u)) {
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(400);
		}
	}
	
	
	
	
}
