package cz.upce.fei.cv05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){ return "error"; }

    @GetMapping("/")
    public String showAllPosts(Model model){
        return "index";
    }
}
