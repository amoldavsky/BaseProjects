package com.projetx.web.api.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import com.projetx.logic.api.service.UserService;
import com.projetx.logic.api.service.impl.UserServiceImpl;
import com.projetx.web.api.config.spring.AppTestConfig;
import com.projetx.web.api.service.UserRest;
import com.projetx.web.api.service.impl.UserRestImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.env.MockPropertySource;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebTestContextBootstrapper;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ContextConfiguration( 
		loader = AnnotationConfigContextLoader.class, 
		classes = { AppTestConfig.class }
)
@WebAppConfiguration
//@BootstrapWith(WebTestContextBootstrapper.class)
public class UserRestTest {
	
	/*
	@Autowired
	WebApplicationContext ctx;
	*/
	
	//@Autowired
	UserService userService;
	
	UserRest userRestRS;
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		userService = Mockito.mock( UserService.class );
		userRestRS = Mockito.spy( new UserRestImpl() );
		ReflectionTestUtils.setField( userRestRS, "userService", userService );
		
		System.out.println( "assaf00:" + userService );
		
	}

	@After
    public void tearDown() throws Exception {

	}

	@Test
	public void testGettersSetters() {
		
		Mockito.doReturn( null ).when( userService ).getUser( 11 );
		System.out.println( "assaf01:" + userRestRS.getUser( 11 ) );
		
	}
	/*
	@Test
	public void testGetUser() {

	}
	
	@Test
	public void testCreateUser() {

	}
	
	@Test
	public void testUpdateUser() {

	}
	
	@Test
	public void testDeleteUser() {

	}
	
	@Test
	public void testGetUsers() {

	}
	
	@Test
	public void testDeleteUsers() {

	}
	*/
}
