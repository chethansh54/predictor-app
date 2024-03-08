package project.app.datainjector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.app.datainjector.model.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}
