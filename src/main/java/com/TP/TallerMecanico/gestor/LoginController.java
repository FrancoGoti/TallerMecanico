package com.TP.TallerMecanico.gestor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login.html"; // Aquí "login" es el nombre de la vista HTML (login.html)
    }
}