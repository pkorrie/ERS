package com.revature;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.repositories.ReimbDao;
import com.revature.services.ReimbService;

@ExtendWith(MockitoExtension.class)

public class ReimbServiceTest {

	
	@Mock
	private ReimbDao rd;
	
	@InjectMocks
	private ReimbService rs;
}
