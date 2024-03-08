package project.predictor.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.predictor.app.model.LoginHistory;
import project.predictor.app.service.LoginHistoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/history")
public class LoginHistoryController {
    private LoginHistoryService loginHistoryService;

    public LoginHistoryController(LoginHistoryService loginHistoryService) {
        super();
        this.loginHistoryService = loginHistoryService;
    }

    @PostMapping("loginstore")
    public ResponseEntity<Map> saveLoginHistory(@RequestBody LoginHistory loginHistory) {
        LoginHistory createdLoginHistory = loginHistoryService.saveLoginHistory(loginHistory);
        Map<String, String> responseData = new HashMap<>();

        if (createdLoginHistory != null) {
            responseData.put("message", "success");
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.put("message", "failed");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("loginhistory")
    public ResponseEntity<List<LoginHistory>> getLoginHistory() {
        List<LoginHistory> allHistory = loginHistoryService.getAllLoginHistory();
        return new ResponseEntity<>(allHistory, HttpStatus.OK);
    }
}
