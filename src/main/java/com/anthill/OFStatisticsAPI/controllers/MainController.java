package com.anthill.OFStatisticsAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String index(){

        return "OFStatistics API works normally!";
    }
}
