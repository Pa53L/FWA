package edu.school21.cinema.servlets;


import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/signup"})
public class SignUp extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserService.class);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/jsp/signup.jsp");
        dis.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String phoneNumber = request.getParameter("phonenumber");
        String pass = request.getParameter("userpass");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        if (userService.signUp(phoneNumber, pass, firstName, lastName, request.getRemoteAddr())) {
            User sessionUser = userService.getProfile(phoneNumber);
            HttpSession session = request.getSession();
            session.setAttribute("user", sessionUser);
            session.setAttribute("auth", userService.getAuth(sessionUser.getPhoneNumber()));
            response.sendRedirect("profile");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
