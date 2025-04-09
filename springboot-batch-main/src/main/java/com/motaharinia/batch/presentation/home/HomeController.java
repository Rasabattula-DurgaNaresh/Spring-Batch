package com.motaharinia.batch.presentation.home;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @Value("${spring.application.name}")
    private String springApplicationName;


    @GetMapping("/")
    public String getUrl() {
        return springApplicationName;
    }



}
