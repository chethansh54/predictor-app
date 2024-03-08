package project.predictor.app.controller;

import org.springframework.web.bind.annotation.*;
import project.predictor.app.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.predictor.app.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("users")
    public ResponseEntity<Map> saveUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        Map<String, String> responseData = new HashMap<>();

        if (createdUser != null) {
            responseData.put("message", "success");
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.put("message", "failed");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("userauth")
    public ResponseEntity<Map> authUsers(
            @RequestParam(name = "uname") String userName,
            @RequestParam(name = "password") String passwordValue) {
        List<User> allUsers = userService.getAllUsers();
        boolean authSuccess = false;

        for (User user : allUsers) {
            if ((user.getUserName().equals(userName)) && (user.getPassword().equals(passwordValue))) {
                authSuccess = true;
                break;
            }
        }

        Map<String, String> responseData = new HashMap<>();

        if (authSuccess) {
            responseData.put("auth", "success");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.put("auth", "failed");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

    }
}
