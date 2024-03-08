package project.predictor.app.repository;

import org.springframework.stereotype.Repository;
import project.predictor.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
