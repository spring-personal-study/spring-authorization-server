package com.bos.server.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @GetMapping("/users/login")
    public String signInBluebirdPage() {
        return "bluebird-signin";
    }

    @GetMapping("/oauth2/login")
    public String signInOauth2Page() {
        return "oauth2-signin";
    }

    @PostMapping("/oauth2/sign-in")
    public String processLogin(@RequestBody String username, @RequestBody String password) {
        // Handle the login logic

        // Example validation
        if (username == null || password == null) {
            System.out.println("auth failed!!");
            return "oauth2-signin";
        }

        // Your login logic goes here

        // Redirect to the appropriate page on success
        return "redirect:/oauth2/authorization";
    }
/*
    @PostMapping("/users/sign-in")
    public String signIn(@RequestBody String username, @RequestBody String password) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return "hello";
    }
*/


}
