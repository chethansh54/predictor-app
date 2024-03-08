package project.predictor.app.service;

import project.predictor.app.model.SensorData;

import java.util.List;

public interface SensorDataService {
    SensorData saveSensorData(SensorData sensorData);

    List<SensorData> getAllSensorData();
}
