package com.bos.server.oauth.controller;

import com.bos.server.oauth.model.dto.ClientRegistrationDto;
import com.bos.server.oauth.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register-client";
    }

    @PostMapping("/register")
    public String registerClient(ClientRegistrationDto clientRegistrationDto, RedirectAttributes redirectAttributes) {
        clientService.registerClient(clientRegistrationDto);
        redirectAttributes.addFlashAttribute("message", "Client registration successful. Please login.");
        return "redirect:/login";
    }
}
