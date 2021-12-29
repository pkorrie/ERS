package com.revature.drivers;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.post;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controllers.AuthController;
import com.revature.controllers.EmployeeController;
import com.revature.controllers.ManagerController;
import com.revature.controllers.ReimbController;


public class Driver {

	private static Logger log = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		log.info("The Main Method has been called.");
		Javalin app = Javalin.create( (config) -> {
			config.enableCorsForAllOrigins();
		
		}).start();
		
		app.before(ctx -> {
		    ctx.header("Access-Control-Allow-Headers", "Authorization");
		    ctx.header("Access-Control-Expose-Headers", "Authorization");
		});
		
		app.routes(() -> {
			
			path("auth", () ->{
				post(AuthController::login);
			});	
			
			// /manager
			path("manager", () -> {
				path("employees", () ->{
					get(ManagerController::getAllEmployees);
				});
				
				path("update", () -> {	
					put(ManagerController::updateEmployeeInfo); //An Employee can view their information
					
				});
				
				path("{id}", () -> {
					get(ManagerController::getEmployeeById);
				});
				
				
			});
			
			path("reimbs", () -> {
				path("{id}", () -> {
					get(ReimbController:: getAllReimbById);
				});
				
			});
			
//			 /employee
			path("employee", () -> {
			
				path("{id}", () -> {
					get(EmployeeController::getEmployeeById);
					});
				path("update", ()->{				
						put(EmployeeController::updateEmployee);
					});
				
			});
			// /reimb
			path("reimb", () ->{
//				path("{author}", () -> {
//					get(ReimbController:: getAllReimb);
//				});
				
				path("request", ()->{				
					post(ReimbController::makeReqt);
				});
				
				path("getAllReimb", ()->{				
					get(ReimbController:: getAllReimb);
				});
				
				path("pending", () -> {
					get(ReimbController:: getAllPendingReimb);
				});
				
				path("resolved", () -> {
					get(ReimbController:: getAllResolvedReimb);
				});
				
				path("resolvedRqt", () -> {
					get(ReimbController:: getAllResolvedReqt);
				});
				
				path("{id}", ()->{				
					put(ReimbController::update);
				});
				
			});
				
		});				
				
	}
}
