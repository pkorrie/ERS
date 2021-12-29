package com.revature;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;



@ExtendWith(MockitoExtension.class)

public class UserDaoTest {

	@SuppressWarnings("unused")
	private UserDao ud = new UserPostgres();
	
	@Test
	public void getAllEmployees() {
		
		//System.out.println(ud.getAllEmployee().toString());
		
		
	}
	
	
	}
	








//private static MockedStatic<ConnectionUtil> mockedConnectionUtil;
//private static Connection con;
//
//@BeforeAll
//public static void init() {
//	mockedConnectionUtil = Mockito.mockStatic(ConnectionUtil.class);
//	mockedConnectionUtil.when(ConnectionUtil::getConnectionFromFile())
//	.then(I -> getH2Connection());
//}
//
//@AfterAll
//public static void end() {
//	
//	mockedConnectionUtil.close();
//}
//
//@BeforeEach
//public void setUp() {
//	try(Connection c = ConnectionUtil.getConnectionFromFile()){
//		RunScript.execute(c, new FileReader("setup.sql"));
//	} catch (SQLException | FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}