package com.cookingmama.cookingmamaclient.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class CustomHeaderFilter implements Filter {

    @Autowired
    HttpSession session;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest((HttpServletRequest) request);
        if (session != null) {
            mutableRequest.putHeader("Authorization", "Bearer " + session.getAttribute("Token"));
        }
//        mutableRequest.putHeader("Authorization", "Bearer "+session.getAttribute("Token"));
        chain.doFilter(mutableRequest, response);
    }
}
