package edu.school21.cinema.servlets;

import edu.school21.cinema.repositories.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

public class UsersServlet extends HttpServlet {
    private UserRepository userService;

    @Override
    public void init(ServletConfig config) {
//        ServletContext context = config.getServletContext();
//        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
//        this.userService = springContext.getBean(UserService.class);
    }
}
