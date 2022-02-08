package com.anthill.OFStatisticsAPI.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Index")
@RestController
public class MainController {

    @GetMapping("/")
    public String index(){

        return "OFStatistics API works normally!";
    }
}
