package com.codeup.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        model.addAttribute("cohortNames", cohortNames);
        return "/join";
    }

    @GetMapping("/roll-dice")
    public String showRoll(Model model){
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        model.addAttribute("numbers", numbers);
        return "/roll-dice";
    }

    @GetMapping("/roll-dice/{number}")
    public String numberPicker(@PathVariable(name = "number") int number, Model model){
        model.addAttribute("number", number);
        Random random = new Random();
        if (number == random.nextInt(6)+1){
            model.addAttribute("Correct", "Correct");
        }


        model.addAttribute("number", number);
        return "/roll-dice";
    }



}
