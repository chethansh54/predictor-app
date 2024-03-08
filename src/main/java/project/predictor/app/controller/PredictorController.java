package project.predictor.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictorController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to PredictorApp REST API";
    }

    @GetMapping("/error")
    public String errorMsg() {
        return "PredictorApp REST API: unknown error";
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello";
    }

    @GetMapping("/authuser/query")
    public String authenticateUser(
            @RequestParam(name = "uname") String userName,
            @RequestParam(name = "password") String passwordValue
    ) {
        return "User Auth: Success!";
    }
}
