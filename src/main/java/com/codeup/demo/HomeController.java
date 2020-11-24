package com.codeup.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String sayHello() {
        return "This is the landing page!";
    }

    @GetMapping("/home")
    public String welcome(){
        return "/home";
    }

    @GetMapping("/hello")
    public String hello(){
        return "/hello";
    }

    @GetMapping("/create-a-hello")
    @ResponseBody
    public String showCreate(){
        return "create form";
    }

    @GetMapping("/hello/{name}") //request
    public String sayHello(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "/hello";
    }

    @GetMapping("/join")
    public String showJoinForm(Model model){
        List<String> cohortNames = new ArrayList<>();
        cohortNames.add("COBOL");
        cohortNames.add("Draco");

        model.addAttribute("cohortNames", cohortNames);
        return "/join";
    }

    @PostMapping("/join")
    public String postJoinForm(@RequestParam(name = "cohort") String cohort, Model model){
        List<String> cohortNames = new ArrayList<>();
        cohortNames.add("COBOL");
        cohortNames.add("Draco");


        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "/join";
    }


}
