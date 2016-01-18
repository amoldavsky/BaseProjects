package com.projectx.web.api.searvice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.catalina.core.ApplicationContext;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.local.LocalConduit;
import org.apache.cxf.jaxrs.client.WebClient;

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

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.projectx.logic.api.service.UserService;
import com.projectx.logic.api.service.impl.UserServiceImpl;
import com.projectx.web.api.service.JaxrsService;
import com.projectx.web.api.service.UserRest;
import com.projectx.web.api.service.impl.UserRestImpl;
import com.projectx.web.api.test.config.spring.AppTestConfig;
import com.projectx.web.api.test.config.spring.ConfigTestUtils;
import com.projetx.sdk.user.User;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
		classes = { UserRestTest.class, AppTestConfig.class }
)
@WebAppConfiguration
//@BootstrapWith(WebTestContextBootstrapper.class)
public class UserRestTest extends BaseTest implements ApplicationContextAware {
	
	/*
	@Autowired
	WebApplicationContext ctx;
	*/
	
	//@Autowired
	UserService userService;
	
	@Autowired @Qualifier("ConfigUTesttils")
	ConfigTestUtils configUtil;
	
	UserRest userRestRS;
	
	ApplicationContext applicationContext;
	
	@Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }
	
	@Bean
	public ApplicationContext applicationContext() {
		return this.applicationContext;
	}
	
	private Server jaxRsServer() {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
	     //sf.setResourceClasses( MyJaxrsResource.class );
	     
	     List<Object> providers = new ArrayList<Object>();
	     // add custom providers if any
	     sf.setProviders(providers);
        
        //factory.setServiceBean(new DenialCategoryRest());
        
		// get all the class annotated with @JaxrsService
        List<Object> beans = configUtil.findBeans( JaxrsService.class );
        //List<Class> beansClasses = configUtil.findClasses( JaxrsService.class );

		if (beans.size() > 0) {
			
			// add all the CXF service classes into the CXF stack
			sf.setResourceClasses( UserRestImpl.class );
			sf.setServiceBeans( beans );
			sf.setAddress( "/api" );
			//sf.setBus( springBus );
			sf.setStart(true);
			
			// set JSON as the response serializer
			JacksonJsonProvider provider = new JacksonJsonProvider();
			sf.setProvider( provider );
	        
		}
        
		return sf.create();
    }
	
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
	
	@Configuration
	@ComponentScan(
			basePackages = {
				"com.projectx.web.api.test",
				"com.projetx.web.api.service"
			}
	)
	public static class TestContextConfig {
		
	}

	@Test
	public void testGettersSetters() {
		
		Mockito.doReturn( null ).when( userService ).getUser( 11 );
		System.out.println( "assaf01:" + userRestRS.getUser( 11 ) );
		
		Server server = jaxRsServer();
		
		WebClient client = WebClient.create( "http://localhost:8080/api" );
		WebClient.getConfig(client).getRequestContext().put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);

		client.accept( "application/json" );
		client.path("/user/11");
		User user = client.get( User.class );
		//assertEquals(123L, book.getId());
		
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
