package project.predictor.app.service.impl;

import org.springframework.stereotype.Service;
import project.predictor.app.model.User;
import project.predictor.app.repository.UserRepository;
import project.predictor.app.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
