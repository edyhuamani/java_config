package pe.com.foxdev.config;

import javax.activation.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
//@Profile(value="dev")
public class DevDatabaseConfig {
	
	
//	@Bean(name="dataSource")
//	public DataSource dataSource(){
//		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//        dsLookup.setResourceRef(true);
//        DataSource dataSource = (DataSource) dsLookup.getDataSource("jdbc/yourJdbcGoesHere");
//        return dataSource;
//	}
}
