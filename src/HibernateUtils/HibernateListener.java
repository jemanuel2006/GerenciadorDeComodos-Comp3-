package HibernateUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by juane on 18/09/2016.
 */
public class HibernateListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent event) {
        SessionFactoryBuilder.getSessionFactory(); // Just call the static initializer of that class
    }

    public void contextDestroyed(ServletContextEvent event) {
        SessionFactoryBuilder.getSessionFactory().close(); // Free all resources
    }
}
