package com.siat.post.util;

import jakarta.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

//@Component
//@WebFilter(urlPatterns = {"/index.html", "/protected/*"})
public class AuthCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String path = httpRequest.getRequestURI();

        if (path.equals("/login") || path.equals("/login") || path.startsWith("/css") || path.startsWith("/js")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("sessionUser") == null) {
            httpResponse.sendRedirect("/login.html");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}