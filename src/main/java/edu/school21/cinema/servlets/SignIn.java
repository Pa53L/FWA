package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet("/signIn")
public class SignIn extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/html/signin.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("phonenumber");
        String pass = request.getParameter("userpass");
        if (userService.signIn(user, pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("auth", user);
            response.sendRedirect("profile");
        } else {
            response.sendRedirect("signin");
        }
    }

}

