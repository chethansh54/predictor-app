package project.app.datainjector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.app.datainjector.model.SensorRawData;

@Repository
public interface SensorRawDataRepository extends JpaRepository<SensorRawData, Long> {
}
