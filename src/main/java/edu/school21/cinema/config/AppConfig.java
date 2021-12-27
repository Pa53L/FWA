package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserRepositoryImpl;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
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

//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(dbUrl, dbUser, dbPassword);
//        dataSource.setDriverClassName(driver);
////        Resource resourceSchema = new ClassPathResource("/sql/schema.sql");
////        Resource resourceData = new ClassPathResource("/sql/data.sql");
////        ResourceDatabasePopulator databasePopulatorSchema = new ResourceDatabasePopulator(resourceSchema);
////        ResourceDatabasePopulator databasePopulatorData = new ResourceDatabasePopulator(resourceData);
////        databasePopulatorSchema.execute(dataSource);
////        databasePopulatorData.execute(dataSource);
//        return dataSource;
//    }


    @Bean
    public JdbcTemplate getJDBCTemplate(DataSource ds) {
        return  new JdbcTemplate(ds, false);
    }

    @Bean
    public UserService getUserService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

//    @Value("${storage.path}")
//    private String UPLOAD_PATH;

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dbUrl);
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setUsername(dbUser);
        hikariConfig.setPassword(dbPassword);
        return new HikariDataSource(hikariConfig);
    }

//    @Bean
//    BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    UserRepository userRepository(DataSource dataSource) {
        return new UserRepositoryImpl(dataSource);
    }

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

//    @Bean
//    String uploadPath() {
//        return UPLOAD_PATH;
//    }
}
