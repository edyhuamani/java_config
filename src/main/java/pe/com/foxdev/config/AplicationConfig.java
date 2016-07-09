package pe.com.foxdev.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;

import pe.com.foxdev.aspect.AspectLogger;
import pe.com.foxdev.interceptor.SpringInterceptor;

@Configuration
@ComponentScan(basePackages="pe.com.foxdev") // context:component-scan base-package=”com.luckyryan.sample”/>
@EnableWebMvc // <mvc:annotation-driven/>
@EnableAspectJAutoProxy
@Import(DevDatabaseConfig.class) // importa clase con configuracion db
//@ImportResource("classpath:/config/spring-web-servlet.xml")
@PropertySource("classpath:application.properties")
public class AplicationConfig extends WebMvcConfigurerAdapter{
	
	@Autowired 
	private DevDatabaseConfig devDataConfig;
	
//	@Bean(name="jdbcTemplate")
//	public JdbcTemplate jdbcTemplate(){
//
//		JdbcTemplate jdbcTemplate=new JdbcTemplate();
//		jdbcTemplate.setDataSource((javax.sql.DataSource) devDataConfig.dataSource());
//		return jdbcTemplate;
//	}
//	
	@Bean(name="restTemplate")
	public RestTemplate restTemplate(){
		RestTemplate restTemplate=new RestTemplate();
	    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    
	    converters.add(new StringHttpMessageConverter());
	    converters.add(new MappingJackson2XmlHttpMessageConverter());
    
	    restTemplate.setMessageConverters(converters);
	    //converters.add(marshallingMessageConverter());
		return restTemplate;
	}
	
	@Bean
	public AspectLogger getLogger(){
		AspectLogger logger=new AspectLogger();
		return logger;
	}

//	@Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        mappingJackson2HttpMessageConverter.setPrettyPrint(true);
//        return mappingJackson2HttpMessageConverter;
//    }
//	
//	@Bean
//    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
//    	MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter = new MappingJackson2XmlHttpMessageConverter();
//    	mappingJackson2XmlHttpMessageConverter.setPrettyPrint(true);
//        return mappingJackson2XmlHttpMessageConverter;
//    }  
//	
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//	
//		super.configureMessageConverters(converters);
//		converters.add(mappingJackson2HttpMessageConverter());
//	    converters.add(mappingJackson2XmlHttpMessageConverter());
//	}
	
	
//	@Bean(name="txManager")
//	public PlatformTransactionManager  txManager(){
//		PlatformTransactionManager txManager=new DataSourceTransactionManager(devDataConfig.dataSource());
//		
//		return txManager;
//	}
	
	@Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	 }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		super.addViewControllers(registry);
		registry.addRedirectViewController("/", "/index.jsp");
	}
	
	
	@Bean

	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
	    handlerMapping.setOrder(0);
	    handlerMapping.setInterceptors(new Object[]{new SpringInterceptor()}); // <-- This was missing
	    return handlerMapping;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SpringInterceptor()).addPathPatterns("/*");
		super.addInterceptors(registry);
	}
}
