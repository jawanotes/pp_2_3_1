package crud.config;

import crud.model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(value = "crud")
@PropertySource("classpath:db.properties")
public class AppConfig implements WebMvcConfigurer {
    private final Environment envt;

    private final ApplicationContext applicationContext;

    public AppConfig(ApplicationContext applicationContext, Environment envt) {
        this.applicationContext = applicationContext;
        this.envt = envt;
    }

    @Bean
    public DataSource makeDbcp2DataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(envt.getProperty("db.driver"));
        basicDataSource.setUrl(envt.getProperty("db.url"));
        basicDataSource.setUsername(envt.getProperty("db.username"));
        basicDataSource.setPassword(envt.getProperty("db.password"));
        return basicDataSource;
    }

    @Bean
    @Primary
    public DataSource makeDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(envt.getProperty("db.driver"));
        dataSource.setUrl(envt.getProperty("db.url"));
        dataSource.setUsername(envt.getProperty("db.username"));
        dataSource.setPassword(envt.getProperty("db.password"));
        return dataSource;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(makeDataSource());
        //emfb.setPackagesToScan("crud.model");
        emfb.setPackagesToScan(User.class.getPackageName());
        emfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties props = new Properties();
        props.put("hibernate.show_sql", envt.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", envt.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.dialect", envt.getProperty("hibernate.dialect"));
        props.put("characterEncoding", envt.getProperty("characterEncoding"));
        props.put("useUnicode", envt.getProperty("useUnicode"));
        emfb.setJpaProperties(props);

        return emfb;
    }

    @Bean
    public EntityManager getEntityManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return entityManagerFactoryBean.getObject().createEntityManager();
    }

    @Bean
    public JpaTransactionManager getTransactionManager() {
/*        JpaTransactionManager transactionManager = new JpaTransactionManager();
        //HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        //transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;*/
        return new JpaTransactionManager();
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        //templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setPrefix("/WEB-INF/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("text/html; charset=UTF-8");
        registry.viewResolver(resolver);
    }


}
