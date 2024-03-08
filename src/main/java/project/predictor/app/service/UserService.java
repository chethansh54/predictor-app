package project.predictor.app.service;

import project.predictor.app.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
}
