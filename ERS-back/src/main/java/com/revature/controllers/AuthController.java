package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.services.UserService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class AuthController {
	private static UserService us = new UserService();
	private static Logger log = LogManager.getRootLogger();

	
	public static void login(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		String token = null;
		
		if(username != null && password != null) {
				token = us.login(username, password);
		}
		
		if(token != null) {
			ctx.header("Authorization", token);
			ctx.status(HttpCode.OK);
			log.info("Someone had logged in");

		} else {
			ctx.status(HttpCode.NOT_FOUND);
		}
	}
}
