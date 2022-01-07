package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/signin", "/signIn", "/signup", "/signUp", "/profile"})
public class AppFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);
        if(session == null && !(uri.endsWith("html") || uri.endsWith("signin") || uri.endsWith("signIn")
                || uri.endsWith("signup") || uri.endsWith("signUp"))){
            res.sendRedirect("/WEB-INF/html/signIn.html");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
