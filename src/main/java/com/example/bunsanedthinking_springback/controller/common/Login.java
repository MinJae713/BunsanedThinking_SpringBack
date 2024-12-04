package com.example.bunsanedthinking_springback.controller.common;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Login {

    private String host = "localhost";

    @GetMapping("/")
    public void redirectVoid(HttpServletResponse response) throws IOException {
        // 외부 URL로 리다이렉트
        response.sendRedirect(getFrontend(host));
    }
    private String getFrontend(String host) {
        return "http://"+host+":63342/bunsanedThinking_Front/html/" +
                "common/login.html?_ijt=aotmhn65amo6rkpgvu9ithio0v&_ij_reload=RELOAD_ON_SAVE";
    }
}
