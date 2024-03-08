package project.predictor.app.service.impl;

import org.springframework.stereotype.Service;
import project.predictor.app.model.SensorRawData;
import project.predictor.app.repository.SensorRawDataRepository;
import project.predictor.app.service.SensorRawDataService;

import java.util.List;

@Service
public class SensorRawDataServiceImpl implements SensorRawDataService {

    private SensorRawDataRepository sensorRawDataRepository;

    public SensorRawDataServiceImpl(SensorRawDataRepository sensorRawDataRepository) {
        super();
        this.sensorRawDataRepository = sensorRawDataRepository;
    }

    @Override
    public SensorRawData saveSensorRawData(SensorRawData sensorRawData) {
        return sensorRawDataRepository.save(sensorRawData);
    }

    @Override
    public List<SensorRawData> getAllSensorRawData() {
        return sensorRawDataRepository.findAll();
    }
}
