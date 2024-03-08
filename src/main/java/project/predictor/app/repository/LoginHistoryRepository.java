package project.predictor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.predictor.app.model.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}
