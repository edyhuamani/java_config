package pe.com.foxdev.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author Edy
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(value="pe.com.foxdev")
public class DispatcherConfig  extends WebMvcConfigurerAdapter{
	

	
	/**
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setSuffix(".jsp");
		urlBasedViewResolver.setPrefix("/WEB-INF/jsp/");
		urlBasedViewResolver.setViewClass(JstlView.class);
		return urlBasedViewResolver;
	}
	
	/**
	 * @return
	 */
	@Bean
	public PropertyPlaceholderConfigurer  getPropertyPlaceHolder(){
		PropertyPlaceholderConfigurer  propertyPlaceHolder=new PropertyPlaceholderConfigurer ();
		Resource[] locations=new ClassPathResource[]{new ClassPathResource("servers.properties"),new ClassPathResource("java.properties"),new ClassPathResource("application.properties")}; 
		propertyPlaceHolder.setLocations(locations);
		return propertyPlaceHolder; 
	}
	
}
