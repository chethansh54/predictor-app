package project.predictor.app.service;

import project.predictor.app.model.LoginHistory;

import java.util.List;

public interface LoginHistoryService {
    LoginHistory saveLoginHistory(LoginHistory loginHistory);

    List<LoginHistory> getAllLoginHistory();
}
