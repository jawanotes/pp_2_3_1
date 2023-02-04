package crud.config;

import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(value = "crud")
@PropertySource("classpath:db.properties")
public class AppConfig {
    @Autowired
    private Environment envt;

    private final ApplicationContext applicationContext;

    public AppConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Bean
    public DataSource makeDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setDriverClassName(envt.getProperty("db.driver"));
        dataSource.setUrl(envt.getProperty("db.url"));
        dataSource.setUsername(envt.getProperty("db.username"));
        dataSource.setPassword(envt.getProperty("db.password"));
        return dataSource;

    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(makeDataSource());
        emfb.setPackagesToScan("model");
        emfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties props=new Properties();
        props.put("hibernate.show_sql", envt.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", envt.getProperty("hibernate.hbm2ddl.auto"));
        emfb.setJpaProperties(props);

        return emfb;
    }

/*    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }*/

}
