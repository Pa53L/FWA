package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserRepositoryImpl;
import edu.school21.cinema.services.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    private ApplicationContext springContext;
    @Value("${storage.path}")
    private String uploadPath;
    UserRepository userRepository;

    @Override
    public void init(ServletConfig config) {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        userRepository = springContext.getBean("userRepository", UserRepository.class);
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        File imagesDir = new File(uploadPath + user.getId());
        if (!imagesDir.exists())
            imagesDir.mkdir();
        request.setAttribute("uploadUserPath", uploadPath + user.getId());
        File image = new File(uploadPath + user.getId());
        for (File file : image.listFiles())
            if (file.getName().contains("DS_Store"))
                file.delete();
        if ((image.listFiles().length != 0)) {
            File[] files = image.listFiles();
            Arrays.sort(files, (f1, f2) -> Long.valueOf(f1.lastModified()).compareTo(f2.lastModified()));
            byte[] fileContent = FileUtils.readFileToByteArray(files[files.length - 1]);
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            request.setAttribute("image", encodedString);
        }
//        UserService userService = springContext.getBean("userService", UserService.class);
//        session.setAttribute("auth", userService.getAuth(((User)session.getAttribute("user")).getLogin()));
//        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
//        dispatcher.forward(req, resp);


        UserService userService = springContext.getBean("userService", UserService.class);
        session.setAttribute("auth", userService.getAuth(user.getPhoneNumber()));
        if (session.getAttribute("user") != null) {
            RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
            dis.forward(request, response);
        } else { // not logged in
            response.sendRedirect("signin.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        File imagesDir = new File(uploadPath + user.getId());
        for (Part part : req.getParts()) {
            part.write(imagesDir + File.separator + part.getSubmittedFileName());
        }
        req.setAttribute("uploadUserPath", uploadPath + user.getId());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        dispatcher.forward(req, resp);
    }
// other code
}
