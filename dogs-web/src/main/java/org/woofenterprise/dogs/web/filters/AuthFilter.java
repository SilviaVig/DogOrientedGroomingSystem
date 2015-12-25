package org.woofenterprise.dogs.web.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.woofenterprise.dogs.dto.CustomerAuthenticationDTO;
import org.woofenterprise.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.facade.CustomerFacade;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * Created by Silvia.Vigasova on 25.12.2015.
 */

@WebFilter(urlPatterns = {"/", "/customers/*", "/dogs/*", "/appointments/*"})
public class AuthFilter implements Filter {

    final static Logger log = LoggerFactory.getLogger(AuthFilter.class);


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
        String authenticated = (String) request.getSession().getAttribute("authenticated");
        if (authenticated == null) {
            response.sendRedirect("/pa165/auth/login");
        }
        if (authenticated.equals("admin")) {

        } else if (authenticated.equals("user")) {
            if (!request.getMethod().equals("GET")) {
                response.getWriter().println("<html><body><h1>401 Unauthorized</h1> You are not authorized to view this page.</body></html>");
            }
        } else {
            response.sendRedirect("/pa165/auth/login");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}