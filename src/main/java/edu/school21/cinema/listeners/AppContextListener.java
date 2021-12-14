package edu.school21.cinema.listeners;

import edu.school21.cinema.config.AppConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        context.setAttribute("springContext", ctx);
    }
}
