package project.predictor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.predictor.app.model.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}
