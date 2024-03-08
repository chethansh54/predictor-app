package project.app.datainjector.service;

import project.app.datainjector.model.SensorRawData;

import java.util.List;

public interface SensorRawDataService {
    SensorRawData saveSensorRawData(SensorRawData sensorRawData);

    List<SensorRawData> getAllSensorRawData();
}
