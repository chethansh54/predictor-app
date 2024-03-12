package project.predictor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.predictor.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
