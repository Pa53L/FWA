package edu.school21.cinema.servlets;

import edu.school21.cinema.repositories.UserRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;

public class UsersServlet extends HttpServlet {
    private UserRepository userService;

    @Override
    public void init(ServletConfig config) {
//        ServletContext context = config.getServletContext();
//        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
//        this.userService = springContext.getBean(UserService.class);
    }
}
