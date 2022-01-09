package edu.school21.cinema.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:../application.properties")
public class AppConfig {

    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.user}")
    private String dbUser;
    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(dbUrl, dbUser, dbPassword);
        dataSource.setDriverClassName(driver);
        Resource resourceSchema = new ClassPathResource("/sql/schema.sql");
        Resource resourceData = new ClassPathResource("/sql/data.sql");
        ResourceDatabasePopulator databasePopulatorSchema = new ResourceDatabasePopulator(resourceSchema);
        ResourceDatabasePopulator databasePopulatorData = new ResourceDatabasePopulator(resourceData);
        databasePopulatorSchema.execute(dataSource);
        databasePopulatorData.execute(dataSource);
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJDBCTemplate(DataSource dataSource) {
        return  new JdbcTemplate(dataSource, false);
    }

}
