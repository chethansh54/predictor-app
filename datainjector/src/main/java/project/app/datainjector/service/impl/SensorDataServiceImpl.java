package project.app.datainjector.service.impl;

import org.springframework.stereotype.Service;
import project.app.datainjector.model.SensorData;
import project.app.datainjector.repository.SensorDataRepository;
import project.app.datainjector.service.SensorDataService;

import java.util.List;

@Service
public class SensorDataServiceImpl implements SensorDataService {

    private SensorDataRepository sensorDataRepository;

    public SensorDataServiceImpl(SensorDataRepository sensorDataRepository) {
        super();
        this.sensorDataRepository = sensorDataRepository;
    }

    @Override
    public SensorData saveSensorData(SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }

    @Override
    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }
}
