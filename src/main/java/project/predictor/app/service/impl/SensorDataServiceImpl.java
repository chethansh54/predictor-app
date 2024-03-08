package project.predictor.app.service.impl;

import org.springframework.stereotype.Service;
import project.predictor.app.model.SensorData;
import project.predictor.app.model.User;
import project.predictor.app.repository.SensorDataRepository;
import project.predictor.app.repository.UserRepository;
import project.predictor.app.service.SensorDataService;
import project.predictor.app.service.UserService;

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
