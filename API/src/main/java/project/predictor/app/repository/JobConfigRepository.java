package project.predictor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.predictor.app.model.JobConfig;

@Repository
public interface JobConfigRepository extends JpaRepository<JobConfig, Long> {
}
