package project.predictor.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.predictor.app.model.SensorRawData;

@Repository
public interface SensorRawDataRepository extends JpaRepository<SensorRawData, Long> {
}
