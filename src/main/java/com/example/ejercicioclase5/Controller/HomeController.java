package com.example.ejercicioclase5.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"","/"})
    public String vistaPrincipal(){
        return "principal";
    }
}
