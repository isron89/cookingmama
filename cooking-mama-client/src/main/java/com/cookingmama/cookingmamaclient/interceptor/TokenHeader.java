package com.cookingmama.cookingmamaclient.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Service
public class TokenHeader {
    HttpHeaders headers = new HttpHeaders();

    public void setTokenHeader(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String Token = (String) session.getAttribute("Token");
        if (Token != null) {
            headers.set("Authorization", "Bearer " + Token);
            request.setAttribute("Authorization", "Bearer " + Token);
            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + Token);
        }
    }

    public HttpHeaders createHeaders(String token){
        return new HttpHeaders() {{
            String authHeader = "Bearer " + token;
            set( "Authorization", authHeader );
        }};
    }
}