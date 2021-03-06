package help_desk_app.config;

import help_desk_app.entity.*;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
public class HibernateUtils {
    private final static String URL = "jdbc.url";
    private final static String DB_DRIVER = "jdbc.driverClassName";
    private static final String CREATE_STRATEGY = "hibernate.hbm2ddl.auto";
    private final static String DIALECT = "hibernate.dialect";
    private final static String SHOW_SQL = "hibernate.show_sql";
    private final static String USER_NAME = "jdbc.username";
    private final static String PASSWORD = "jdbc.password";
    private final static String PACKAGE = "help_desk_app";

    private final Environment environment;

    public HibernateUtils(Environment environment) {
        this.environment = environment;
    }

    /**
     * Create the bean for building the Session factory
     * @return the {@link LocalSessionFactoryBean} that creates a Hibernate SessionFactory
     */
    @Bean
    @Lazy
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setAnnotatedClasses(Attachment.class, Comment.class,
                Feedback.class, History.class,
                Ticket.class, User.class);
        sessionFactory.setPackagesToScan(PACKAGE);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * Returns the factory for connections to the physical data source that this DataSource object represents.
     * @return the{@link DataSource}
     */
    @Bean
    @Lazy
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(DB_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(URL));
        dataSource.setUsername(environment.getRequiredProperty(USER_NAME));
        dataSource.setPassword(environment.getRequiredProperty(PASSWORD));
        return dataSource;
    }

    /**
     * @return the{@link HibernateTransactionManager} for transactional actions with data base
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    /**
     * Returns the property object for hibernate configure
     * @return the {@link Properties} object
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(DIALECT, environment.getRequiredProperty(DIALECT));
        properties.put(SHOW_SQL, environment.getRequiredProperty(SHOW_SQL));
        properties.put(CREATE_STRATEGY, environment.getRequiredProperty(CREATE_STRATEGY));
        return properties;
    }
}
