package com.projetx.web.api.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.projetx.web.api.config.spring.AppConfig;
import com.projetx.web.api.config.spring.CxfConfig;
import com.projetx.web.api.config.spring.WebInitializer;


@Configuration

@ComponentScan(
		basePackages = {
			"com.kur8or.logic.api",
			"com.kur8or.web.api.test"
		},
		useDefaultFilters = false,
		excludeFilters = {
				@Filter( type=FilterType.ASSIGNABLE_TYPE, value=WebInitializer.class ),
				@Filter( type=FilterType.ASSIGNABLE_TYPE, value=CxfConfig.class ),
				@Filter( type=FilterType.ANNOTATION, value=EnableWebMvc.class ),
				@Filter( type=FilterType.ANNOTATION, value=Configuration.class )
		})
@EnableTransactionManagement
public class AppTestConfig extends AppConfig {

}
