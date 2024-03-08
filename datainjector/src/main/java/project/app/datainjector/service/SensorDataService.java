package project.app.datainjector.service;

import project.app.datainjector.model.SensorData;

import java.util.List;

public interface SensorDataService {
    SensorData saveSensorData(SensorData sensorData);

    List<SensorData> getAllSensorData();
}
