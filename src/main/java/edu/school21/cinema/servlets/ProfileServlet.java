package edu.school21.cinema.servlets;

import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    private ApplicationContext springContext;
    private String uploadPath;
    UserRepository userRepository;

    @Override
    public void init(ServletConfig config) {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        userRepository = springContext.getBean("userRepository", UserRepository.class);

        request.getSession().setAttribute("USER", userRepository.getByPhoneNumber("+79312222222"));
        HttpSession sess = request.getSession();
//        if (sess.getAttribute("USER") != null) {
            RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
            dis.forward(request, response);
//        } else { // not logged in
//            response.sendRedirect("SignIn.jsp");
//        }
    }
// other code
}
