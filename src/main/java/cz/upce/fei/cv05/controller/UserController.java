package cz.upce.fei.cv05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){ return "error"; }
}
