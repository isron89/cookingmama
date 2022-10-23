package com.cookingmama.cookingmamaclient.interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
//import com.google.common.base.Strings;

public class LoggerInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);
    /**
     * Executed before actual handler is executed
     **/
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        log.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI()); //+ getParameters(request)
        HttpSession session = request.getSession();
        String Token = (String) session.getAttribute("Token");
//        if (Token != null) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + Token);
//            request.setAttribute("Authorization", "Bearer " + Token);
//            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + Token);
//        }
        if (session.getAttribute("Token") == null) {
            log.info("Token is null");
        } else {
            log.info("Token is not null");
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + "Token");
            request.setAttribute("Authorization", "Bearer " + Token);
            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + Token);
            log.info("Session: " + session.getId());
        }
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + Token);
//            HttpHeaders headers =  (HttpHeaders) session.getAttribute("headers");
//        log.info("Token: " + session.getAttribute("Token"));
//        log.info("Token2: " + Token);
//        log.info("Token3: " + request.getAttribute("Authorization"));
//        notwork
//        System.out.println("headers: " + session.getAttribute("headers"));
//        System.out.println("header: " + request.getHeader("Authorization"));
        log.info("User id: " + session.getAttribute("Userid"));
        log.info("Username: " + session.getAttribute("Username"));
        return true;
    }
    /**
     * Executed before after handler is executed
     **/
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
        log.info("[postHandle][" + request + "]");
    }
    /**
     * Executed after complete request is finished
     **/
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
        if (ex != null)
            ex.printStackTrace();
        log.info("[afterCompletion][" + request + "][exception: " + ex + "]");
    }
//    private String getParameters(final HttpServletRequest request) {
//        final StringBuffer posted = new StringBuffer();
//        final Enumeration<?> e = request.getParameterNames();
//        if (e != null)
//            posted.append("?");
//        while (e != null && e.hasMoreElements()) {
//            if (posted.length() > 1)
//                posted.append("&");
//            final String curr = (String) e.nextElement();
//            posted.append(curr)
//                    .append("=");
//            if (curr.contains("password") || curr.contains("answer") || curr.contains("pwd")) {
//                posted.append("*****");
//            } else {
//                posted.append(request.getParameter(curr));
//            }
//        }
//        final String ip = request.getHeader("X-FORWARDED-FOR");
//        final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
//        if (!Strings.isNullOrEmpty(ipAddr))
//            posted.append("&_psip=" + ipAddr);
//        return posted.toString();
//    }
    private String getRemoteAddr(final HttpServletRequest request) {
        final String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }
}