package project.predictor.app.service;

import project.predictor.app.model.SensorRawData;

import java.util.List;

public interface SensorRawDataService {
    SensorRawData saveSensorRawData(SensorRawData sensorRawData);

    List<SensorRawData> getAllSensorRawData();
}
