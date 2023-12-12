package com.bos.server.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "signup.html";
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody String username, @RequestBody String password) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return "hello.";
    }

}
