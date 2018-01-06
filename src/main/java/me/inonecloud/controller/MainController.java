package me.inonecloud.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Author Andrew Yelmanov
 * @Date 06.01.2018
 */


@RestController
public class MainController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
