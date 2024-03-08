package project.predictor.app.service.impl;

import org.springframework.stereotype.Service;
import project.predictor.app.model.LoginHistory;
import project.predictor.app.repository.LoginHistoryRepository;
import project.predictor.app.service.LoginHistoryService;

import java.util.List;

@Service
public class LoginHistoryServiceimpl implements LoginHistoryService {

    private LoginHistoryRepository loginHistoryRepository;

    public LoginHistoryServiceimpl(LoginHistoryRepository loginHistoryRepository) {
        super();
        this.loginHistoryRepository = loginHistoryRepository;
    }

    @Override
    public LoginHistory saveLoginHistory(LoginHistory loginHistory) {
        return loginHistoryRepository.save(loginHistory);
    }

    @Override
    public List<LoginHistory> getAllLoginHistory() {
        return loginHistoryRepository.findAll();
    }
}
