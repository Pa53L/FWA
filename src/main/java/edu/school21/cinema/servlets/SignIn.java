package edu.school21.cinema.servlets;

import edu.school21.cinema.repositories.UserRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

@WebServlet("/signin")
public class SignIn extends HttpServlet {

    private UserRepository userService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserRepository.class);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        response.setContentType("text/html");
    }

}

